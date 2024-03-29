package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Ask;
import com.example.domain.Book;
import com.example.domain.Complain;
import com.example.domain.Login;
import com.example.domain.Merchant;
import com.example.domain.Notice;
import com.example.domain.RecordDTO;
import com.example.domain.User;
import com.example.service.AskService;
import com.example.service.BookService;
import com.example.service.ComplainService;
import com.example.service.FaqService;
import com.example.service.LoginService;
import com.example.service.MerchantService;
import com.example.service.NoticeService;
import com.example.service.RecordService;
import com.example.service.TermsService;
import com.example.service.UserService;

@Controller
public class AdminController {

    // 회원
    @Autowired
    private UserService userService;

    // 도서
    @Autowired
    private BookService bookService;

    // 컴플레인
    @Autowired
    private ComplainService complainService;

    // 공지사항
    @Autowired
    private NoticeService noticeService;

    // FAQ
    @Autowired
    private FaqService faqService;

    // 문의
    @Autowired
    private AskService askService;

    // 이용약관
    @Autowired
    private TermsService termsService;

    // 독서기록
    @Autowired
    private RecordService recordService;

    // 오늘 총 매출
    @Autowired
    private MerchantService merchantService;

    // 오늘 접속자 수
    @Autowired
    private LoginService loginService;

    @RequestMapping("/{step}")
    public String viewPage(@PathVariable String step) {
        return step;
    }

    // *************************************** 메인 ****************************************
    // 메인 제재명단, 문의건수
    @RequestMapping("/adminmain")
    public String index(Model m) {
        List<User> user = userService.memberList();                             // 제재명단
        m.addAttribute("memberList", user);
        List<Ask> dailyAsk = askService.dailyAsk();                             // 일별 문의건수
        m.addAttribute("dailyAsk", dailyAsk);
        List<User> getAge = userService.getAge();                               // 연령대 분석
        m.addAttribute("getAge", getAge);
        List<Book> bookRank = bookService.bookRank();                           // 도서 인기순위
        m.addAttribute("bookRank", bookRank);
        List<Merchant> todaySum = merchantService.todaySum();                   // 오늘 총 매출
        m.addAttribute("todaySum", todaySum.get(0));
        List<User> newUser = userService.newUser();                             // 신규 가입자 수
        m.addAttribute("newUser", newUser.get(0));
        List<Login> todayLogin = loginService.todayLogin();                     // 오늘 접속자 수
        m.addAttribute("todayLogin", todayLogin.get(0));
        return "adminmain";
    }

    // *************************************** 회원 ****************************************
    // 회원관리
    @RequestMapping("/member")
    public void memberList(Model m) {
        List<User> list = userService.memberList();
        m.addAttribute("memberList", list);
    }

    // 회원상세정보
    @RequestMapping("/memberDetail")
    public void memberDetail(Model m, User vo) {
        User member = userService.memberDetail(vo);
        m.addAttribute("member", member);
    }

    // 회원정보수정
    @RequestMapping("/updateMember")
    public String updateMember(User vo) {
        userService.updateMember(vo);
        return "redirect:member";
    }

    // 회원정보삭제
    @RequestMapping("/deleteMember")
    public String deleteMember(User vo) {
        userService.deleteMember(vo);
        return "redirect:member";
    }

    // *************************************** 도서 ****************************************
    // 도서관리
    @RequestMapping("/book")
    public void bookList(Model m) {
        List<Book> list = bookService.bookList();
        m.addAttribute("bookList", list);
    }

    // 도서상세정보
    @RequestMapping("/bookDetail")
    public void bookDetail(Model m, Book vo) {
        Book book = bookService.bookDetail(vo);
        m.addAttribute("book", book);
    }

    // 도서정보수정
    @RequestMapping("/updateBook")
    public String updateBook(Book vo) {
        bookService.updateBook(vo);
        return "redirect:book";
    }

    // 도서정보삭제
    @RequestMapping("/deleteBook")
    public String deleteBook(Book vo) {
        bookService.deleteBook(vo);
        return "redirect:book";
    }

    // *************************************** 컴플레인 ****************************************
    // 컴플레인 신고관리
    @RequestMapping("/complain")
    public void complainList(Model m) {
        List<Complain> list = complainService.complainList();
        m.addAttribute("complainList", list);
    }

    // 컴플레인 상세정보
    @RequestMapping("/complainDetail")
    public void complainDetail(Model m, Complain vo) {
        Complain complain = complainService.complainDetail(vo);
        m.addAttribute("complain", complain);

        // 관리자 회원 ID 목록 중 member_grade가 admin인 것만 가져오기
        List<String> adminMemberIds = userService.getAdminMemberIds();
        m.addAttribute("adminMemberIds", adminMemberIds);
    }

    // 컴플레인 정보수정
    @RequestMapping("/updateComplain")
    public String updateComplain(Complain vo) {
        complainService.updateComplain(vo);
        return "redirect:complain";
    }

    // *************************************** 공지사항 ****************************************
    // 공지사항 관리
    @RequestMapping("/notice")
    public void noticeList(Model m) {
        List<Notice> list = noticeService.noticeList();
        m.addAttribute("noticeList", list);
    }

    // 공지사항 상세정보
    @RequestMapping("/noticeDetail")
    public void noticeDetail(Model m, Notice vo) {
        Notice notice = noticeService.noticeDetail(vo);
        m.addAttribute("notice", notice);

        // 관리자 회원 ID 목록 중 member_grade가 admin인 것만 가져오기
        List<String> adminMemberIds = userService.getAdminMemberIds();
        m.addAttribute("adminMemberIds", adminMemberIds);
    }

    // 공지사항 정보수정
    @RequestMapping("/updateNotice")
    public String updateNotice(Notice vo) {
        noticeService.updateNotice(vo);
        return "redirect:notice";
    }

    // 공지사항 삭제
    @RequestMapping("/deleteNotice")
    public String deleteNotice(Notice vo) {
        noticeService.deleteNotice(vo);
        return "redirect:notice";
    }

    // 공지사항 등록
    @RequestMapping("/insertNotice")
    public void insertNotice(Model m, Notice vo) {
        noticeService.insertNotice(vo);

        // 관리자 회원 ID 목록 중 member_grade가 admin인 것만 가져오기
        List<String> adminMemberIds = userService.getAdminMemberIds();
        m.addAttribute("adminMemberIds", adminMemberIds);
    }

    // *************************************** 이용약관 ****************************************
    // 이용약관 관리
    @RequestMapping("/terms")
    public void termsList(Model m) {
        List<Notice> list = termsService.termsList();
        m.addAttribute("termsList", list);
    }

    // 이용약관 상세정보
    @RequestMapping("/termsDetail")
    public void termsDetail(Model m, Notice vo) {
        Notice terms = termsService.termsDetail(vo);
        m.addAttribute("terms", terms);

        // 관리자 회원 ID 목록 중 member_grade가 admin인 것만 가져오기
        List<String> adminMemberIds = userService.getAdminMemberIds();
        m.addAttribute("adminMemberIds", adminMemberIds);
    }

    // 이용약관 정보수정
    @RequestMapping("/updateTerms")
    public String updateTerms(Notice vo) {
        termsService.updateTerms(vo);
        return "redirect:terms";
    }

    // *************************************** FAQ ****************************************
    // FAQ 관리
    @RequestMapping("/faq")
    public void faqList(Model m) {
        List<Notice> list = faqService.faqList();
        m.addAttribute("faqList", list);
    }

    // FAQ 상세정보
    @RequestMapping("/faqDetail")
    public void faqDetail(Model m, Notice vo) {
        Notice faq = faqService.faqDetail(vo);
        m.addAttribute("faq", faq);

        // 관리자 회원 ID 목록 중 member_grade가 admin인 것만 가져오기
        List<String> adminMemberIds = userService.getAdminMemberIds();
        m.addAttribute("adminMemberIds", adminMemberIds);
    }

    // FAQ 정보수정
    @RequestMapping("/updateFaq")
    public String updateFaq(Notice vo) {
        faqService.updateFaq(vo);
        return "redirect:faq";
    }

    // FAQ 삭제
    @RequestMapping("/deleteFaq")
    public String deleteFaq(Notice vo) {
        faqService.deleteFaq(vo);
        return "redirect:faq";
    }

    // FAQ 등록
    @RequestMapping("/insertFaq")
    public void insertFaq(Model m, Notice vo) {
        faqService.insertFaq(vo);

        // 관리자 회원 ID 목록 중 member_grade가 admin인 것만 가져오기
        List<String> adminMemberIds = userService.getAdminMemberIds();
        m.addAttribute("adminMemberIds", adminMemberIds);
    }

    // *************************************** 문의 ****************************************
    // 문의 관리
    @RequestMapping("/ask")
    public String askList(Model m) {
        List<Ask> list = askService.askList();
        m.addAttribute("askList", list);
        return "ask";
    }

    // 문의 상세정보
    @RequestMapping("/askDetail")
    public void askDetail(Model m, Ask vo) {
        Ask ask = askService.askDetail(vo);
        m.addAttribute("ask", ask);

        // 관리자 회원 ID 목록 중 member_grade가 admin인 것만 가져오기
        List<String> adminMemberIds = userService.getAdminMemberIds();
        m.addAttribute("adminMemberIds", adminMemberIds);
    }

    // 문의 정보수정
    @RequestMapping("/updateAsk")
    public String updateAsk(Ask vo) {
        askService.updateAsk(vo);
        return "redirect:ask";
    }

    // 문의 삭제
    @RequestMapping("/deleteAsk")
    public String deleteAsk(Ask vo) {
        askService.deleteAsk(vo);
        return "redirect:ask";
    }

    // 문의 등록
    @RequestMapping("/insertAsk")
    public void insertAsk(Model m, Ask vo) {
        askService.insertAsk(vo);

        // 관리자 회원 ID 목록 중 member_grade가 admin인 것만 가져오기
        List<String> adminMemberIds = userService.getAdminMemberIds();
        m.addAttribute("adminMemberIds", adminMemberIds);
    }

    // *************************************** 독서기록 ****************************************
    // 독서기록 관리
    @RequestMapping("/record")
    public void recordList(Model m) {
        List<RecordDTO> list = recordService.recordList();
        m.addAttribute("recordList", list);
    }

    // 독서기록 상세정보
    @RequestMapping("/recordDetail")
    public void recordDetail(Model m, RecordDTO vo) {
        RecordDTO record = recordService.recordDetail(vo);
        m.addAttribute("record", record);
    }

    // 독서기록 정보수정
    @RequestMapping("/updateRecord")
    public String updateRecord(RecordDTO vo) {
        recordService.updateRecord(vo);
        return "redirect:record";
    }

    // 독서기록 정보삭제
    @RequestMapping("/deleteRecord")
    public String deleteRecord(RecordDTO vo) {
        recordService.deleteRecord(vo);
        return "redirect:record";
    }

    // *************************************** 통계 ****************************************
    // 통계관리
    @RequestMapping("/charts")
    public void charts() {
    }

    // *************************************** 관리자 도서관 ****************************************
    // 도서관관리
    @RequestMapping("/adminlibrary")
    public void adminlibrary() {
    }
   
}

