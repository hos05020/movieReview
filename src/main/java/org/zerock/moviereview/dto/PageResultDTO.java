package org.zerock.moviereview.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO,EN> {

    private List<DTO> dtoList;

    private int totalPage;

    private int page;

    private int size;

    private int start,end;

    private boolean prev , next;

    private List<Integer> pageList;

    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber()+1;
        this.size = pageable.getPageSize();

        int tempend = (int)(Math.ceil(page/10.0))*10;
        start = tempend-9;
        prev = start >1;
        end = totalPage > tempend?tempend:totalPage;

        next = totalPage>tempend;
        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
    }


}
