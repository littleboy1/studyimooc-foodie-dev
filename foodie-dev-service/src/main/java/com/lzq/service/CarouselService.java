package com.lzq.service;

import com.lzq.pojo.Carousel;

import java.util.List;

public interface CarouselService {

    List<Carousel> queryAll(Integer isShow);
}
