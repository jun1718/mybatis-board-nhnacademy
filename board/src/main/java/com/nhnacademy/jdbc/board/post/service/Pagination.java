package com.nhnacademy.jdbc.board.post.service;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int displayNum;
    private int nowPage;
    private int totalContent;

    private int endPage;

    public Pagination(int displayNum, int nowPage, int totalContent) {
        this.displayNum = displayNum;
        this.nowPage = nowPage;
        this.totalContent = totalContent;

        pagination();
    }

    private void pagination() {
        if (Objects.isNull(nowPage) || nowPage <= 1) {
            nowPage = 1;
        }

        if (totalContent % displayNum == 0) {
            endPage = totalContent / displayNum;
        } else {
            endPage = totalContent / displayNum + 1;
        }

        if (nowPage > endPage) nowPage = endPage;
    }
}
