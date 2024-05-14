package com.example.hippobookproject.service.mypage;

import com.example.hippobookproject.dto.mypage.BookContainerDto;
import com.example.hippobookproject.dto.mypage.IntBoardDto;
import com.example.hippobookproject.dto.mypage.IntProfileDto;
import com.example.hippobookproject.mapper.user.MypageBookContainerMapper;
import com.example.hippobookproject.mapper.user.MypageMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MypageServiceTest {

    @Mock
    MypageMapper mypageMapper;

    @Mock
    MypageBookContainerMapper mypageBookContainerMapper;

    @InjectMocks
    MypageService mypageService;

    @Test
    void findProfile() {
        // given
        IntProfileDto dto = new IntProfileDto();
        dto.setUserNickName("닉네임");
        doReturn(Optional.of(dto)).when(mypageMapper).selectProfile(any());
        // when
        IntProfileDto profileDto = mypageService.findProfile(1L);
        // then
        assertThat(profileDto).extracting("userNickName").isEqualTo("닉네임");

    }

    @Test
    void resisterIntBoardText() {
        //given
        Mockito.doNothing().when(mypageMapper).insertIntBoardText(any());
        // when
        mypageService.registerIntBoardText(new IntBoardDto());
        // then
        Mockito.verify(mypageMapper, Mockito.times(1)).insertIntBoardText(any());
    }

    @Test
    void findIntBoardText() {
        // given
        IntBoardDto boardDto = new IntBoardDto();
        boardDto.setIntBoardContent("안녕하세요");
        doReturn(Optional.of(boardDto)).when(mypageMapper).selectIntBoardText(any());
        // when
        IntBoardDto intBoardDto = mypageService.findIntBoardText(1L);
        // then
        assertThat(intBoardDto).extracting("intBoardContent").isEqualTo("안녕하세요");

    }

    @Test
    void findBookContainer() {
        // give
        doReturn(List.of(new BookContainerDto())).when(mypageBookContainerMapper).selectBookContainer(any());
        // when
        List<BookContainerDto> bookContainerList = mypageService.findBookContainer(1L);
        // then
        assertThat(bookContainerList).hasSize(1);
    }

    @Test
    void removeBookContainer() {
        //given
        doNothing().when(mypageBookContainerMapper).deleteBookHas(any());
        //when
        mypageService.removeBookContainer(1L);
        //then
        verify(mypageBookContainerMapper, times(1)).deleteBookHas(any());

    }

    @Test
    void findBestBook() {
        // give
        BookContainerDto dto = new BookContainerDto();
        dto.setBookHasId(3L);
        doReturn(Optional.of(dto)).when(mypageBookContainerMapper).selectBestBook(any());
        // when
        BookContainerDto bookContainerDto = mypageService.findBestBook(1L);
        // then
        assertThat(bookContainerDto).extracting("bookHasId").isEqualTo(3L);
    }

    @Test
    void modifyBestBook() {
        //given
        doNothing().when(mypageBookContainerMapper).updateBestBook(any());
        // when
        mypageService.modifyBestBook(new BookContainerDto());
        // then
        verify(mypageBookContainerMapper, times(1)).updateBestBook(any());
    }

    @Test
    void modifyBookStatus() {
        doNothing().when(mypageBookContainerMapper).updateBookStatus(any());

        mypageService.modifyBookStatus(new BookContainerDto());

        verify(mypageBookContainerMapper, times(1)).updateBookStatus(any());

    }

    @Test
    void findRecentBook() {
        doReturn(List.of(new BookContainerDto())).when(mypageMapper).selectRecentBook(any());

        List<BookContainerDto> recentBookList = mypageService.findRecentBook(1L);

        assertThat(recentBookList).hasSize(1);
    }
}
