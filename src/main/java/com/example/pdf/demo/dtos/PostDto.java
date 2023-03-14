package com.example.pdf.demo.dtos;

import java.util.Objects;

public class PostDto {
    private String caption;

    private String type;


    public PostDto() {
    }

    public PostDto(String caption, String type) {
        this.caption = caption;
        this.type = type;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PostDto caption(String caption) {
        setCaption(caption);
        return this;
    }

    public PostDto type(String type) {
        setType(type);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PostDto)) {
            return false;
        }
        PostDto postDto = (PostDto) o;
        return Objects.equals(caption, postDto.caption) && Objects.equals(type, postDto.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caption, type);
    }

    @Override
    public String toString() {
        return "{" +
            " caption='" + getCaption() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }

}
