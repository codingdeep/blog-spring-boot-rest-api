package com.blog.payloads;
import java.util.List;



public class PostApiResponse {
    private List<PostDto> data;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalRecords;
    private Integer totalPages;
    private boolean isLastPage;
    public PostApiResponse(){

    }

    public void setData(List<PostDto> data){
        this.data = data;
    }
    public List<PostDto> getData(){return data;}

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
    public Integer getPageNumber(){return pageNumber;}

    public void setPageSize(Integer pageSize){this.pageSize = pageSize;}

    public Integer getPageSize(){return pageSize;}

    public void setTotalRecords(Long totalRecords){
        this.totalRecords = totalRecords;
    }

    public Long getTotalRecords(){
        return  totalRecords;
    }

    public void setTotalPages(Integer totalPages){
        this.totalPages = totalPages;
    }

    public Integer getTotalPages(){
        return  totalPages;
    }

    public void setLastPage(boolean isLastPage){
        this.isLastPage = isLastPage;
    }
    public boolean getLastPage(){
        return  isLastPage;
    }


}
