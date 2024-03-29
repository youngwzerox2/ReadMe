import * as React from 'react';
// import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Button from '@mui/material/Button';
// import Container from '@mui/material/Container';
// import GitHubIcon from '@mui/icons-material/GitHub';
// import FacebookIcon from '@mui/icons-material/Facebook';
// import XIcon from '@mui/icons-material/X';
// import { createTheme, ThemeProvider } from '@mui/material/styles';
// import Header from './Header';
// import MainFeaturedPost from './MainFeaturedPost';
import CardActionArea from '@mui/material/CardActionArea';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
// import Main from './Main';
// import Sidebar from './Sidebar';
// import Footer from './Footer';
// import post1 from './blog-post.1.md';
// import post2 from './blog-post.2.md';
// import post3 from './blog-post.3.md';
import Typography from '@mui/material/Typography';
// import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import Checkbox from '@mui/material/Checkbox';
import FavoriteBorder from '@mui/icons-material/FavoriteBorder';
import Favorite from '@mui/icons-material/Favorite';
import LocalLibraryIcon from '@mui/icons-material/LocalLibrary';
import { Link } from '@mui/material';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';

import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import axios from 'axios';
import { useEffect } from 'react';
import { useState } from 'react';
// const sections = [
//   { title: 'Technology', url: '#' },
//   { title: 'Design', url: '#' },
//   { title: 'Culture', url: '#' },
//   { title: 'Business', url: '#' },
//   { title: 'Politics', url: '#' },
//   { title: 'Opinion', url: '#' },
//   { title: 'Science', url: '#' },
//   { title: 'Health', url: '#' },
//   { title: 'Style', url: '#' },
//   { title: 'Travel', url: '#' }
// ];

// const mainFeaturedPost = {
//   title: 'Title of a longer featured blog post',
//   description:
//     "Multiple lines of text that form the lede, informing new readers quickly and efficiently about what's most interesting in this post's contents.",
//   image: 'https://source.unsplash.com/random?wallpapers',
//   imageText: 'main image description',
//   linkText: 'Continue reading…'
// };

// const featuredPosts = [
//   {
//     title: 'Featured post',
//     date: 'Nov 12',
//     description: 'This is a wider card with supporting text below as a natural lead-in to additional content.',
//     image: 'https://source.unsplash.com/random?wallpapers',
//     imageLabel: 'Image Text'
//   }
// ];

// const posts = [post1, post2, post3];

// const sidebar = {
//   title: 'About',
//   description:
//     'Etiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.',
//   archives: [
//     { title: 'March 2020', url: '#' },
//     { title: 'February 2020', url: '#' },
//     { title: 'January 2020', url: '#' },
//     { title: 'November 1999', url: '#' },
//     { title: 'October 1999', url: '#' },
//     { title: 'September 1999', url: '#' },
//     { title: 'August 1999', url: '#' },
//     { title: 'July 1999', url: '#' },
//     { title: 'June 1999', url: '#' },
//     { title: 'May 1999', url: '#' },
//     { title: 'April 1999', url: '#' },
//   ],
//   social: [
//     { name: 'GitHub', icon: GitHubIcon },
//     { name: 'X', icon: XIcon },
//     { name: 'Facebook', icon: FacebookIcon },
//   ],
// }

// TODO remove, this demo shouldn't need to reset the theme.
// const defaultTheme = createTheme();
const label = { inputProps: { 'aria-label': 'Checkbox demo' } };

export default function Blog({ isbn }) {
  const libraryBtn = () => {
    axios.get(`/library/memberlibrary?isbn13=${isbn}`);
  };
  // var isbn13 = props.isbn;
  const [bookc, setBookc] = useState([]);
  const bookLoad = () => {
    axios
      .get('/book/selectByNum', { params: { bookIsbn13: isbn } })
      .then((re) => {
        console.log(re.data);
        setBookc(re.data);
      })
      .catch((err) => console.log('에러 :', err));
  };
  useEffect(() => {
    bookLoad();
  }, []);

  return (
    // <ThemeProvider theme={defaultTheme}>
    //   <CssBaseline />
    //   <Container maxWidth="lg">
    //     {/* <Header title="도서검색" sections={sections}> */}
    //     <Header title="도서검색" sections={null}>
    //       {/* <img src={Logo} alt="로고 이미지" /> */}
    //     </Header>
    <main>
      {/* <MainFeaturedPost post={mainFeaturedPost} /> */}
      <Grid container spacing={4}>
        <Grid item xs={12} md={12}>
          <CardActionArea component="a" href="#">
            <Card sx={{ display: 'flex', marginRight: '3%', marginLeft: '3%', marginTop: '5%', marginBottom: '0%', padding: '5px' }}>
              <CardMedia component="img" sx={{ width: '30%', display: { xs: 'none', sm: 'block' } }} image={bookc.bookImageUrl} />
              <CardContent sx={{ flex: 1 }}>
                <Typography component="h2" variant="h5" sx={{ margin: '2%' }}>
                  {bookc.bookname}
                </Typography>
                <Typography variant="subtitle1" color="text.secondary" sx={{ marginLeft: '2%' }}>
                  발행년도 : {bookc.publicationYear}
                </Typography>
                <Typography variant="subtitle1" color="text.secondary" sx={{ marginLeft: '2%' }}>
                  저자 : {bookc.authors}
                </Typography>
                <Typography variant="subtitle1" paragraph sx={{ margin: '2%' }}>
                  {bookc.description}
                </Typography>
                <Button sx={{ position: 'absolute', top: '85%', right: '5%' }} variant="outlined" onClick={libraryBtn}>
                  <LocalLibraryIcon />
                  도서관 찾기
                </Button>
              </CardContent>
              <Checkbox {...label} icon={<FavoriteBorder />} checkedIcon={<Favorite />} />
            </Card>
          </CardActionArea>
        </Grid>
        <Grid item xs={12} md={6}>
          <CardActionArea>
            <Card sx={{ display: 'flex', marginLeft: '3%', marginTop: '0%', padding: '5px' }}>
              <List sx={{ width: '100%', bgcolor: 'background.paper' }}>
                <ListItem>
                  <ListItemAvatar>
                    <Avatar>
                      <AccountBoxIcon />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText primary="test50" secondary="독후감 숙제때문에 봄" />
                </ListItem>
                <ListItem>
                  <ListItemAvatar>
                    <Avatar>
                      <AccountBoxIcon />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText primary="싱돌이" secondary="나는 독서한다. 고로 존재한다. 나는 독서한다. 고로 존재..." />
                </ListItem>
                <ListItem>
                  <ListItemAvatar>
                    <Avatar>
                      <AccountBoxIcon />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText primary="아이티" secondary="어린왕자 지금 생각해보면 걍 답답이임." />
                  <Link href="https://www.naver.com">
                    <Typography fontSize="small">더보기</Typography>
                  </Link>
                </ListItem>
              </List>
            </Card>
          </CardActionArea>
        </Grid>
        <Grid item xs={12} md={6}>
          <CardActionArea>
            <Card sx={{ display: 'flex', marginRight: '3%', marginLeft: '3%', marginTop: '0%', padding: '5px' }}>
              <CardContent sx={{ flex: 1 }}>
                <Typography component="h2" variant="h6" sx={{ margin: '2%' }}>
                  <ShoppingCartIcon /> 온라인 서점에서 구매하기
                </Typography>

                <Typography
                  variant="subtitle1"
                  color="text.secondary"
                  sx={{ marginLeft: '2%' }}
                  onClick={() => {
                    window.open(`https://www.yes24.com/Product/Search?domain=ALL&query=${bookc.bookname}`);
                  }}
                >
                  <LocalLibraryIcon fontSize="small" /> 예스24로 이동하기
                </Typography>

                <Typography
                  variant="subtitle1"
                  color="text.secondary"
                  sx={{ marginLeft: '2%' }}
                  onClick={() => {
                    window.open(`https://www.aladin.co.kr/search/wsearchresult.aspx?SearchTarget=Book&SearchWord=${bookc.bookname}`);
                  }}
                >
                  <LocalLibraryIcon fontSize="small" /> 알라딘으로 이동하기
                </Typography>

                <Typography
                  variant="subtitle1"
                  color="text.secondary"
                  sx={{ marginLeft: '2%' }}
                  onClick={() => {
                    window.open(`https://search.kyobobook.co.kr/search?keyword=${bookc.bookname}&gbCode=TOT&target=total`);
                  }}
                >
                  <LocalLibraryIcon fontSize="small" /> 교보문고로 이동하기
                </Typography>
              </CardContent>
            </Card>
          </CardActionArea>
        </Grid>
      </Grid>
    </main>
    //   </Container>
    //   <Footer title="" description="검색결과가 도움이 되었나요?" />
    // </ThemeProvider>
  );
}
