package com.example.demo.common.model;

import lombok.*;

import static java.lang.Math.*;

/**
 * 위 경도 좌표 및 좌표 계산
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PointLonLat {

    private double longitude;

    private double latitude;

    public PointLonLat(String wkt){
        String[] temp = wkt.substring(wkt.indexOf('(') + 1, wkt.indexOf(')')).split(" ");
        this.longitude = Double.parseDouble(temp[0]);
        this.latitude = Double.parseDouble(temp[1]);
    }

    /**
     * 두 위경도 점 -> 거리 (NM)
     * @param p1
     * @param p2
     * @return NM 단위
     */
    public static double distance(PointLonLat p1, PointLonLat p2) {
        double lon1 = toRadians(p1.getLongitude());
        double lat1 = toRadians(p1.getLatitude());
        double lon2 = toRadians(p2.getLongitude());
        double lat2 = toRadians(p2.getLatitude());
        double dPhi = log(tan(PI/4 + lat2/2) / tan(PI/4 + lat1/2));
        double dLat = abs(lat1-lat2);
        double q;
        if(dLat < 1e-10) {
            q = cos(lat1);
        } else {
            q = dLat / dPhi;
        }
        double dLon = abs(lon1-lon2);

        return hypot(dLat, q*dLon) * 6371.0 / 1.852;
    }

    /**
     * 두 위경도 점 -> 각도 (deg)
     * @param p1 시작점
     * @param p2 종료점
     * @return deg 단위 (북쪽 0도, 시계방향 +)
     */
    public static double bearing(PointLonLat p1, PointLonLat p2) {
        double lon1 = toRadians(p1.getLongitude());
        double lat1 = toRadians(p1.getLatitude());
        double lon2 = toRadians(p2.getLongitude());
        double lat2 = toRadians(p2.getLatitude());
        double dLon = lon2 - lon1;
        double dPhi = log(tan(PI/4 + lat2/2) / tan(PI/4 + lat1/2));
        if(abs(dLon) > PI) {
            dLon = dLon > 0 ? -(2*PI - dLon) : (2*PI + dLon);
        }
        return toDegrees(atan2(dLon, dPhi));
    }

    /**
     * 두 위경도 점 -> 중점
     * @param p1
     * @param p2
     * @return
     */
    public static PointLonLat midPoint(PointLonLat p1, PointLonLat p2) {
        double lon1 = toRadians(p1.getLongitude());
        double lat1 = toRadians(p1.getLatitude());
        double lon2 = toRadians(p2.getLongitude());
        double lat2 = toRadians(p2.getLatitude());
        double f1 = tan(PI/4 + lat1/2);
        double f2 = tan(PI/4 + lat2/2);
        double fm = tan(PI/4 + (lat1+lat2/4));
        double lonM;
        if(lat1 - lat2 < 1e-10) {
            lonM = (lon1+lon2)/2;
        } else {
            lonM = ((lon2-lon1)*log(fm) + lon1*log(f2) - lon2*log(f1)) / log(f2/f1);
        }

        return PointLonLat.builder()
                .longitude(toDegrees(lonM))
                .latitude(toDegrees((lat1+lat2)/2))
                .build();
    }

    /**
     * 시작점, 거리(NM), 방향(deg) -> 도착점
     * @param p1
     * @param distance NM
     * @param bearing deg
     * @return
     */
    public static PointLonLat destination(PointLonLat p1, double distance, double bearing) {
        double lon1 = toRadians(p1.getLongitude());
        double lat1 = toRadians(p1.getLatitude());
        double angle = toRadians(bearing);
        double delta = distance * 1.852 / 6371.0;
        double dLat = delta * cos(angle);
        double lat2 = lat1 + dLat;
        double dPhi = log(tan(PI/4 + lat2/2) / tan(PI/4 + lat1/2));
        double q;
        if(dLat < 1e-10) {
            q = cos(lat1);
        } else {
            q = dLat / dPhi;
        }
        double dLon = delta * sin(angle) / q;
        double lon2 = lon1 + dLon;

        return PointLonLat.builder().longitude(toDegrees(lon2)).latitude(toDegrees(lat2)).build();
    }

    /**
     * target 좌표를 origin 점을 중심으로 해서 angle 만큼 회전
     * @param target 대상 좌표
     * @param angle deg 단위 (북쪽 0도, 시계방향 +)
     * @param origin 중심 좌표
     * @return
     */
    public static PointLonLat rotate(PointLonLat target, double angle, PointLonLat origin) {
        double distance = distance(target, origin);
        double bearing = bearing(origin, target);

        return destination(origin, distance, bearing+angle);
    }

}
