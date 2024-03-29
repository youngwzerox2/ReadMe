import { lazy } from 'react';

// project imports
import Loadable from 'ui-component/Loadable';
import MinimalLayout from 'layout/MinimalLayout';

// login option 3 routing
const AuthLogin3 = Loadable(lazy(() => import('views/pages/authentication/mainC/Login3')));
const AuthRegister3 = Loadable(lazy(() => import('views/pages/authentication/mainC/Register3')));

// 시작, 로그인, 회원가입, 메인
const CRegister = Loadable(lazy(() => import('views/pages/authentication/mainC/CRegister')));
const CstartPage = Loadable(lazy(() => import('views/pages/authentication/mainC/CStartPage')));
const CLogin = Loadable(lazy(() => import('views/pages/authentication/mainC/CLogin')));
const CMainpage = Loadable(lazy(() => import('views/pages/authentication/mainC/CMainpage')));

// 마이페이지 - 메인, 회원정보, 결제, 찜, 차단, 고객센터
const CMypage = Loadable(lazy(() => import('views/pages/authentication/mypage/CMypage')));
const CMypage_info = Loadable(lazy(() => import('views/pages/authentication/mypage/CMypageInfo')));
const CMypage_pay = Loadable(lazy(() => import('views/pages/authentication/mypage/CMypagePay')));
const CMypage_liked = Loadable(lazy(() => import('views/pages/authentication/mypage/CMypageLiked')));
const CMypage_notice = Loadable(lazy(() => import('views/pages/authentication/mypage/CMypageNotice')));
const CMypage_block = Loadable(lazy(() => import('views/pages/authentication/mypage/CMypageBlock')));

// 책장 - 메인, 추천하는, 독서기록, 추천받은, 인기도서
const CBookshelf = Loadable(lazy(() => import('views/pages/authentication/bookshelf/CBookshelf')));
const MainRecommanded = Loadable(lazy(() => import('views/pages/authentication/bookshelf/MainRecommanded')));
const MainRecommanding = Loadable(lazy(() => import('views/pages/authentication/bookshelf/MainRecommanding')));
const MainHotBook = Loadable(lazy(() => import('views/pages/authentication/bookshelf/MainHotBook')));
const MainRecord = Loadable(lazy(() => import('views/pages/authentication/bookshelf/MainRecord')));
const DetailKakao = Loadable(lazy(() => import('views/pages/authentication/bookshelf/DetailKakao')));
const Write = Loadable(lazy(() => import('views/pages/authentication/bookshelf/ErecordWrite')));

// 도서관
const CLibrary = Loadable(lazy(() => import('views/pages/authentication/library/CLibrary')));
const RecommandedUser = Loadable(lazy(() => import('views/pages/authentication/library/RecommandedUser')));
const HotUser = Loadable(lazy(() => import('views/pages/authentication/library/UserRanking')));
const SameAge = Loadable(lazy(() => import('views/pages/authentication/library/UserAge')));
const Community = Loadable(lazy(() => import('views/pages/authentication/library/Community')));
const OthersBookshelf = Loadable(lazy(() => import('views/pages/authentication/bookshelf/MainRecommandingTest97')));
// 도서
const Books = Loadable(lazy(() => import('views/pages/authentication/book/BookSearched2')));
const Map = Loadable(lazy(() => import('views/pages/authentication/library/LibraryMap')));
const TestLP = Loadable(lazy(() => import('views/pages/authentication/book/DetailLP')));

// 달력
const Calendar = Loadable(lazy(() => import('views/pages/authentication/calendar/Maincalendar')));

// ==============================|| AUTHENTICATION ROUTING ||============================== //

// 시작페이지 readme/start
// 로그인 readme/login
// 회원가입
// 메인 방
// 책장 메인
// 빈 책장
// 마이페이지 메인
// 도서관 메인페이지

const AuthenticationRoutes = {
  path: '/',
  element: <MinimalLayout />,
  children: [
    {
      path: '/pages/login/login3',
      element: <AuthLogin3 />
    },
    {
      path: '/pages/register/register3',
      element: <AuthRegister3 />
    },
    // =====================================================
    // By ReadMe
    {
      path: '/readme/register',
      element: <CRegister />
    },
    {
      path: '/readme/start',
      element: <CstartPage />
    },
    {
      path: '/readme/login',
      element: <CLogin />
    },
    {
      path: '/readme/main',
      element: <CMainpage />
    },
    // ====================================================
    // ================= || 마이페이지 || ==================
    {
      path: '/readme/mypage',
      element: <CMypage />
    },
    {
      path: 'readme/library',
      element: <CLibrary />
    },
    {
      path: 'readme/mypage/info',
      element: <CMypage_info />
    },
    {
      path: 'readme/mypage/pay',
      element: <CMypage_pay />
    },
    {
      path: 'readme/mypage/blocked',
      element: <CMypage_block />
    },
    {
      path: 'readme/mypage/liked',
      element: <CMypage_liked />
    },
    {
      path: 'readme/cs',
      element: <CMypage_notice /> // 모든 고객센터 메뉴 이씅ㅁ.
    },
    // ==========================================================
    // ====================== || 책장 || ========================
    {
      path: 'readme/bookshelf',
      element: <CBookshelf />
    },
    {
      path: 'readme/bookshelf/recommanded',
      element: <MainRecommanded />
    },
    {
      path: 'readme/bookshelf/recommanding',
      element: <MainRecommanding />
    },
    {
      path: 'readme/bookshelf/hotbook',
      element: <MainHotBook />
    },
    {
      path: 'readme/bookshelf/record',
      element: <MainRecord />
    },
    {
      path: 'readme/bookshelf/detailbook',
      element: <DetailKakao />
    },
    {
      path: 'readme/bookshelf/write',
      element: <Write />
    },

    // ====================================================
    // =================== || 도서관 || ====================
    {
      path: 'readme/library/recommand',
      element: <RecommandedUser />
    },
    {
      path: 'readme/library/hot',
      element: <HotUser />
    },
    {
      path: 'readme/library/age',
      element: <SameAge />
    },
    {
      path: 'readme/library/community',
      element: <Community />
    },
    {
      path: 'readme/library/others',
      element: <OthersBookshelf />
    },
    // =====================================================
    // =================== || 도서 || ======================
    {
      path: 'readme/book',
      element: <Books />
    },
    {
      path: 'readme/map',
      element: <Map />
    },
    {
      path: 'readme/calendar',
      element: <Calendar />
    },
    {
      path: 'readme/book/searched',
      element: <TestLP />
    }
  ]
};

export default AuthenticationRoutes;
