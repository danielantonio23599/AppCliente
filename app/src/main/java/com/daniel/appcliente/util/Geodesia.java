package com.daniel.appcliente.util;

import java.util.Calendar;

/**
 * Created by fernando on 08/12/17.
 */

public class Geodesia {

    /*
      A fórrmula utilizada para determinar a distÃ¢ncia mais curta entre dois pontos no terreno (geodÃ©sicas), se aproxima do geÃ³ide para uma esfera de raio R = 6.372,795477598 km (mÃ©dia quÃ¡drica raio), para o cÃ¡lculo poderia ter uma distÃ¢ncia de erro de 0,3%, especialmente nos extremos polares, e por longas distÃ¢ncias atravÃ©s de vÃ¡rios programas paralelos. Dado dois pontos A e B na esfera expressa pela latitude (lat) e longitude (lon), vocÃª terÃ¡:

      distância (A, B) = R * arccos (sin (lata) * sin (latB) + cos (lata) * cos (latB) * cos (Lona-lonB)) />
      O ângulos utilizados são expressos em radianos, conversÃ£o entre graus e radianos Ã¨ obtido multiplicando-se o Ã¢ngulo por pi e dividir por 180.
      */
    public double distancia1(double lat1, double long1, double lat2, double long2) {

        double R = 6372.795477598f;
        double x = R * Math.acos(Math.sin(toRad(lat1)) * Math.sin(toRad(lat2)) + Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.cos(toRad(long1) - toRad(long2)));
        return x;
    }

    /*
     Calculation of direction between two geographical points
    To determine the direction from the starting point between two points on the earth, use the following formula:

    Δφ = ln( tan( latB / 2 + π / 4 ) / tan( latA / 2 + π / 4) )
    Δlon = abs( lonA - lonB )
    rolamento :  θ = atan2( Δlon ,  Δφ )

    Note: 1) ln = natural log      2) if Δlon > 180°  then   Δlon = Δlon (mod 180).
           3) operation a mod n     4) function atan2(y, x)      5) the angles are in radians
     */
    public double direcao1(double lat1, double long1, double lat2, double long2) {

        double deltaFi= Math.log(Math.tan(toRad(lat2) / 2 + Math.PI / 4) / Math.tan(toRad(lat1) / 2 + Math.PI / 4));
        double deltaLon = Math.abs(toRad(long1) - toRad(long2));
        double rolamento = Math.atan2(deltaLon,deltaFi);

        //double x = Math.atan2((Math.log(Math.tan(toRad(lat2) / 2 + Math.PI / 4) / Math.tan(toRad(lat1) / 2 + Math.PI / 4))), (Math.abs(toRad(long1) - toRad(long2))));
        return rolamento;
        /**
         * 0.010309094522648946 norte: próximo de 0
         * 3.1312835590671444 sul: próximo de 3
         * 1.574058947102817 leste
         * 1.567533706486976 oeste
         *
         */
    }

    /*
     Calculation of the destination point

     To determine the destination point, knowing the starting point the direction Î¸ and the distance d, we use the following formula:

     latB = asin( sin( latA) * cos( d / R ) + cos( latA ) * sin( d / R ) * cos( Î¸ ))
     lonB = lonA + atan2(sin( Î¸ ) * sin( d / R ) * cos( latA ), cos( d / R ) âˆ’ sin( latA ) * sin( latB ))

     Note: 1) function atan2(y, x)      2) the angles are in radians
     */
    public void destino(float latA, float lonA, double direcao) {
    }

    public double distancia2(double latA, double latB, double longA, double longB) {
        double x = 0;
        return x;
    }

    /*
     Distancia no mysql
     -- Select
     -- ParÃ¢metros de entrada
     -- (EndereÃ§o da Vizir Rua Boa Vista 254)
     set @orig_lat= abs(-23.5454668);
     set @orig_lon= abs(-46.633525399999996);
     set @distancia=10;

     SELECT *,
     ((3956 *
     2 *
     ASIN(
     SQRT(POWER(SIN((@orig_lat - abs(dest.latitude)) *
     pi()/180 / 2),2) +
     COS(@orig_lat * pi()/180 ) *
     COS(abs(dest.latitude) * pi()/180) *
     POWER(SIN((@orig_lon - abs(dest.longitude)) *
     pi()/180 / 2), 2))
     )
     ) * 1.609344) as distancia
     FROM endereco dest
     having distancia < @distancia
     ORDER BY distancia
     limit 100;
     */
    /*Formula de haversine Ã© muito usada para distancia entre dois pontos em uma esfera a partir de lat e long
     */
    public double distanciaHaversineKM(double lat1, double lon1, double lat2, double lon2) {
        // TODO Auto-generated method stub
        final int R = 6371; // Radious of the earth
        Double latDistance = toRad(lat2 - lat1);
        Double lonDistance = toRad(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(toRad(lat1)) * Math.cos(toRad(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = R * c;
        return distance;
    }
    public double distanciaHaversineM(double lat1, double lon1, double lat2, double lon2) {
        // TODO Auto-generated method stub
        final int R = 6371; // Radious of the earth
        Double latDistance = toRad(lat2 - lat1);
        Double lonDistance = toRad(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(toRad(lat1)) * Math.cos(toRad(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = (R * c)*1000;
        return distance;
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }

    public double getVelocidade(double distancia, Calendar hora1, Calendar hora2) {
        double s = (hora2.getTimeInMillis() - hora1.getTimeInMillis()) / 1000;//segundos
        double m = s / 60;
        double h = m / 60;
        return h;
    }

    //teormea de pitagoras
    //retorna a distância entre dois pontos considerando a altura
    public double getDistanciaMaisAutitude(double distancia, double altitude1, double altitude2) {
        double diferencaAltura = Math.abs(altitude1 - altitude2);
        //CatetoAÂ² + CatetoBÂ² = hipotenusaÂ²
        double h = Math.sqrt(Math.pow(distancia, 2.0f) + Math.pow(diferencaAltura, 2.0f));
        return h;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // -20.4545971  -45.4473611; -20.4550221  -45.4457253     0.1768548520407467
        Geodesia c = new Geodesia();
        //
        System.out.println("Distancia em km: " + c.distanciaHaversineKM(-20.4545971, -45.4473611, -20.4547683, -45.4469121));//em km
        System.out.println("Distancia em m: " + 1000 * c.distanciaHaversineKM(-20.4545971, -45.4473611, -20.4547683, -45.4469121));//em metros

        System.out.println("Distancia em km: " + c.distancia1(-20.4545971f, -45.4473611f, -20.4547683f, -45.4469121f));//em km
        System.out.println("Distancia em m: " + 1000 * c.distancia1(-20.4545971f, -45.4473611f, -20.4547683f, -45.4469121f));//em metros

    }
}
