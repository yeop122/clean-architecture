package com.example.demo.common.util.modelmapper;

import com.example.demo.common.model.BusinessException;
import com.example.demo.common.model.PointLonLat;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ModelMapper singleton 객체 생성
 * @author yp1222
 */
public class ModelMapperSingleton {

    private static ModelMapper modelMapper;

    private ModelMapperSingleton() {}

    public static ModelMapper getModelMapperInstance() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            // WKT text -> PointLonLat 변환 컨버터
            Converter<String, PointLonLat> wktToPointConverter = new AbstractConverter<>() {
                @Override
                protected PointLonLat convert(String s) {
                    if(s == null) {
                        return null;
                    }

                    // s = POINT(123.45 45.67)
                    String[] coordinates = s.substring(s.indexOf("(") + 1, s.indexOf(")")).split("\\s+");
                    if (coordinates.length == 2) {
                        return PointLonLat.builder()
                                .longitude(Double.parseDouble(coordinates[0]))
                                .latitude(Double.parseDouble(coordinates[1]))
                                .build();
                    } else {
                        throw BusinessException.builder().build();
                    }
                }
            };
            modelMapper.addConverter(wktToPointConverter);

            // WKT text -> List<PointLonLat> 변환 컨버터
            Converter<String, List<PointLonLat>> wktToListConverter = new AbstractConverter<>() {
                @Override
                protected List<PointLonLat> convert(String s) {
                    if(s == null) {
                        return null;
                    }

                    // s = MULTIPOINT((123.45 45.67) (134.56 45.78) ... )
                    List<PointLonLat> list = new ArrayList<>();
                    Pattern pattern = Pattern.compile("\\(([^()]+?)\\)");
                    Matcher matcher = pattern.matcher(s);

                    while (matcher.find()) {
                        String[] coordinates = matcher.group(1).split("\\s+");

                        if (coordinates.length == 2) {
                            list.add(PointLonLat.builder()
                                    .longitude(Double.parseDouble(coordinates[0]))
                                    .latitude(Double.parseDouble(coordinates[1]))
                                    .build());
                        } else {
                            throw BusinessException.builder().build();
                        }
                    }
                    return list;
                }
            };
            modelMapper.addConverter(wktToListConverter);

            // PointLonLat -> WKT text 변환 컨버터
            Converter<PointLonLat, String> pointToWktConverter = new AbstractConverter<>() {
                @Override
                protected String convert(PointLonLat pointLonLat) {
                    if(pointLonLat == null) {
                        return null;
                    }

                    return "POINT(" + pointLonLat.getLongitude() + " " + pointLonLat.getLatitude() + ")";
                }
            };
            modelMapper.addConverter(pointToWktConverter);

            // List<PointLonLat> -> WKT text 변환 컨버터
            Converter<List<PointLonLat>, String> listToWktConverter = new AbstractConverter<>() {
                @Override
                protected String convert(List<PointLonLat> pointLonLats) {
                    if(pointLonLats == null || pointLonLats.isEmpty()) {
                        return null;
                    }

                    StringBuilder sb = new StringBuilder("MULTIPOINT(");
                    for (int i = 0; i < pointLonLats.size(); i++) {
                        if(i > 0) {
                            sb.append(", ");
                        }
                        sb.append("(")
                                .append(pointLonLats.get(i).getLongitude())
                                .append(" ")
                                .append(pointLonLats.get(i).getLatitude())
                                .append(")");
                    }
                    sb.append(")");

                    return sb.toString();
                }
            };
            modelMapper.addConverter(listToWktConverter);
        }

        return modelMapper;
    }
}
