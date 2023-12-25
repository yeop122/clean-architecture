package com.example.demo.common.util.modelmapper;

import org.modelmapper.ModelMapper;

/**
 * ModelMapper 공간데이터 변환
 * @author yp1222
 */
public class ModelMapperWrapper {

    private static final ModelMapper modelMapper = ModelMapperSingleton.getModelMapperInstance();

    /**
     * D 타입으로 매핑
     * @param s source
     * @param destClass destination class (usage: D.class)
     * @return mapped d
     * @param <S>
     * @param <D>
     */
    public static <S, D> D map(S s, Class<D> destClass) {
        return modelMapper.map(s, destClass);
    }

    /**
     * D 타입 데이터에 매핑
     * @param s source
     * @param d destination
     * @param <S>
     * @param <D>
     */
    public static <S, D> void map(S s, D d) {
        modelMapper.map(s, d);
    }

}
