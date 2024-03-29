package com.example.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.ChatData;
import com.example.userservice.UserChatDataServiceImpl;
import com.example.userservice.UserChatLogServiceImpl;

@RestController
@RequestMapping("chatData")
public class ChatDataController {
    
    @Autowired
    private UserChatDataServiceImpl chatDataService;

	@Autowired
	private UserChatLogServiceImpl chatLogService;

    // *** SELECT ***********************************************************
	// 한 명의 사용자가 작성한 모든 채팅 기록 출력
	@GetMapping("/selectByUser")
	public List<ChatData> selectByUser(@RequestParam(name = "memberId") String memberId) {
		try {
			System.out.println("[ChatDataController/selectByUser] 요청");
			List<ChatData> result = chatDataService.selectByUser(memberId);
			System.out.println("[ChatDataController/selectByUser] " + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 특정 채팅 기록 출력
	@GetMapping("/selectByNum")
	public ChatData selectByNum(@RequestParam(name = "chatNumber") String chatNumber) {
		try {
			// System.out.println("[ChatDataController/selectByNum] 요청");
			ChatData result = chatDataService.selectByNum(chatNumber);
			// System.out.println("[ChatDataController/selectByNum] " + result);
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// *** INSERT ***********************************************************
	// 채팅 기록 입력
	@PostMapping("/write")
	public Integer write(ChatData dto) {
		try {
			System.out.println("[ChatDataController/write] 요청");
			Integer result = chatDataService.write(dto);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 사용자의 채팅 내역 입력
	@PostMapping("/recommend")
	public String recommend(@RequestParam(name = "memberId") String memberId) {
		try {
			System.out.println("[ChatDataController/recommend] 요청");

			// DB에서 문장 받아오기
			List<String> sentences;
			Integer log = chatLogService.logYN(memberId);
			if(log == 0) {
				// 이전 채팅 종료 내역이 없으면
				sentences = chatLogService.getSentenceN(memberId);
			} else {
				// 이전 채팅 종료 내역이 있으면
				sentences = chatLogService.getSentenceY(memberId);
			}
			
			// 받아온 문장 하나로 연결
			String sentence = String.join(" ", sentences);
			System.out.println("요청된 문장: " + sentence);

			// .py 파일 실행
			String pythonFile = "src\\main\\resources\\static\\python\\client_emotion.py";
			ProcessBuilder pb = new ProcessBuilder("python.exe", pythonFile, memberId, sentence);
			pb.directory(new File(System.getProperty("user.dir")));
			Process process = pb.start();

			// 프로세스 종료 대기
            int exitCode = process.waitFor();
            System.out.println("Exit Code: " + exitCode);

			// .py 출력 읽어오기
			InputStream inputStream = process.getInputStream();
			InputStream errorStream = process.getErrorStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			BufferedReader error = new BufferedReader(new InputStreamReader(errorStream));
			String line; // 읽어온 각 줄의 내용을 담을 변수
			String errorLine;
			StringBuilder sb = new StringBuilder();

			// 정상 출력
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}

			// 에러 출력
			while ((errorLine = error.readLine()) != null) {
				System.err.println("[Error] > " + errorLine);
			}

			reader.close();
			error.close();

			String output = sb.toString();
			System.out.println(output);
			System.out.println("[Python] > " + output);

			String[] pythonOutput = output.split("\n");
			String emotion = pythonOutput[0];
			String recommended1 = getIsbn(pythonOutput[1]);
			String recommended2 = getIsbn(pythonOutput[2]);
			String recommended3 = getIsbn(pythonOutput[3]);

			// System.out.println(recommended1);

			ChatData ddto = new ChatData();
			ddto.setMemberId(memberId);
			ddto.setEmotion(emotion);
			ddto.setRecommandedBook1(recommended1);
			ddto.setRecommandedBook2(recommended2);
			ddto.setRecommandedBook3(recommended3);

			// 가장 최근에 입력한 chat_number 받아와서 저장
			Integer chatNum = chatLogService.getChatNum(memberId);
			// System.out.println(chatNum);
			ddto.setChatNumber(chatNum);
			// System.out.println("ddto>" + ddto);
			
			// DB에 채팅 데이터 저장
			chatDataService.write(ddto);

			// 마지막 대화 terminate 'Y'로 바꾸기
			chatLogService.terminateY(memberId);

			return "지금 감정에 딱 맞는 책을 책장에 넣어두었어요. 함께 확인해 보실래요?";
		} catch (Exception e) {
			e.printStackTrace();
			return "채팅 기록이 없어요. 오늘의 이야기를 들려주세요.";
		}
	}

	public String getIsbn(String result) {
		// "[]"", " ", "'"를 전부 없애고 ","를 기준으로 자름
		String[] parsedResult = result.replaceAll("[\\[\\]\\s']", "").split(",");
		// 맨 뒤 요소(ISBN)만 return
		return parsedResult[parsedResult.length - 1];
	}

	// *** UPDATE ***********************************************************
	// 추천 책 세 개 중 선택한 책 isbn 저장
	@PostMapping("/pickBook")
	public void pickBook(@RequestParam(name = "chatNumber") String chatNumber,
						 @RequestParam(name = "bookIsbn13") String bookIsbn13) {
		System.out.println("[ChatLogController/pickBook] 요청");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("chatNumber", chatNumber);
		map.put("bookIsbn13", bookIsbn13);
		chatDataService.pickBook(map);
	}

}
