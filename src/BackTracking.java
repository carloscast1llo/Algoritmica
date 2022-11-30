public class BackTracking {

    public static void main(String[] args) {

        int[] vector = new int[]{4, 2, 5, 5, 1, 8};
        int[] vector1 = new int[]{3,3,6,3,3};

        int[] pesos = new int[]{3, 7, 2};
        int[] valores = new int[]{21, 37, 35};

        int[] moneda = new int[]{1, 10, 5};

        int[] cero = new int[]{-4, 3, 5, -5, 0, 1, 8, 10, -21};

        int[] comienzo = new int[]{5, 2, 4, 2, 6, 0, 6, 2};
        int[] fin = new int[]{9, 4, 5, 5, 7, 3, 8, 5};

        int[] pesosExamen = new int[]{1, 3, 2, 5, 1, 4, 2, 1};

       // System.out.println(dosSubconjuntos(vector, 10));
        System.out.println("1.2: " + dosSubconjuntos(vector, 11));
        System.out.println("1.1: " + hayRepartoEquitativo(vector1));

        // Ejercicio Mochila
        int[] resultado = mochilaOptima(pesos, valores, 10);
        System.out.print("Mochila: ");
        for (int i = 0; i < resultado.length; i++) {
            System.out.print(resultado[i] + " ");
        }
        System.out.println();

        // Ejercicio Moneda
        int[] resultadoMoneda = monedaOptima(moneda, 12);
        System.out.print("Moneda: ");
        for (int i = 0; i < resultadoMoneda.length; i++) {
            System.out.print(resultadoMoneda[i] + " ");
        }
        System.out.println();

        boolean[] resultadoCero = subcSuma0MaxElem(cero);
        System.out.print("1.4: ");
        for (int i = 0; i < resultadoCero.length; i++) {
            System.out.print(resultadoCero[i] + " ");
        }
        System.out.println();

        boolean[] resultadoCF = maxUsoRecurso(comienzo, fin);
        System.out.print("1.3: ");
        for (int i = 0; i < resultadoCF.length; i++) {
            System.out.print(resultadoCF[i] + " ");
        }
        System.out.println();

        int[] resultadoExamen = distribucionCarga2(pesosExamen, 10);
        System.out.print("1.5: ");
        for (int i = 0; i < resultadoExamen.length; i++) {
            System.out.print(resultadoExamen[i] + " ");
        }

    }

    public static int[] distribucionCarga2 (int[] pesos, int pMax){
        int[] distribucion = new int[pesos.length];
        int[] mejorDistribucion = new int[pesos.length];
        int contenedor1 = 0;
        int contenedor2 = 0;
        Entero mejorDiferencia= new Entero(Integer.MAX_VALUE);
        for (int i=0; i<distribucion.length;i++){
            distribucion[i]=0; mejorDistribucion[i]=0;
        }
        distribucionCarga2Aux(pesos, pMax, 0, distribucion, mejorDistribucion, contenedor1, contenedor2, mejorDiferencia);

        return mejorDistribucion;
    }

    public static void distribucionCarga2Aux(int[] pesos, int pMax, int nivel, int[] distribucion, int[] mejorDistribucion, int contenedor1, int contenedor2, Entero mejorDiferencia){
        if (nivel==pesos.length){
            if ((Math.abs(contenedor1-contenedor2)) < mejorDiferencia.getValor()) {
                mejorDiferencia.setValor(Math.abs(contenedor1-contenedor2));
                for (int i = 0; i < distribucion.length; i++) {
                    mejorDistribucion[i] = distribucion[i];
                }
            }
        } else{
            for (int c=1; c<3; c++){
                if ( c == 1 && contenedor1 + pesos[nivel] <= pMax || c == 2 && contenedor2 + pesos[nivel] <= pMax){
                    if (c == 1){
                        distribucion[nivel] = c;
                        contenedor1 += pesos[nivel];
                    }
                    if (c == 2){
                        distribucion[nivel] = c;
                        contenedor2 += pesos[nivel];
                    }

                    distribucionCarga2Aux(pesos, pMax, nivel + 1, distribucion, mejorDistribucion, contenedor1, contenedor2, mejorDiferencia);

                    if (c == 1){
                        distribucion[nivel] = c;
                        contenedor1 -= pesos[nivel];
                    }
                    if (c == 2){
                        distribucion[nivel] = c;
                        contenedor2 -= pesos[nivel];
                    }
                }
            }
        }
    }

    // BackTracking
    public static void subconjuntoSumaBack(int[] vector, int num, int[] solucion, int suma,int nivel, Booleano exito){
        if (suma == num){
            exito.setValor(true);
        }else if(nivel < vector.length){
             int c=0;
             while (!exito.getValor() && c < 2){
                 if ( c == 0 || suma + vector[nivel] <= num){
                     solucion[nivel] = c;
                     suma += (vector.length * c);

                     subconjuntoSumaBack(vector, num, solucion,  + 1, suma, exito);

                    if (!exito.getValor()){
                        suma -= (vector.length * c);
                        solucion[nivel] = 0;
                    }

                 }
             }
        }
    }

        // SESION 1.2 - 2 veces
    private static boolean dosSubconjuntos(int[] v, int vObjetivo){
        Booleano exito = new Booleano(false);
        dosSubconjuntosAux(v, vObjetivo, 0,0, 0, exito);
        return exito.getValor();
    }
    public static void dosSubconjuntosAux(int[] v, int vObjetivo, int nivel, int acumA, int acumB, Booleano exito){
        if (acumA == vObjetivo && acumB == vObjetivo){
            exito.setValor(true);
        }else if(nivel < v.length){
            int c = 0; // Casos candidatos
            while (!exito.getValor() && c < 3){
                if (c == 0 || c == 1 && acumA + v[nivel] <= vObjetivo || c == 2 && acumB + v[nivel] <= vObjetivo){
                    if (c == 1){
                        acumA += v[nivel];
                    }else if (c == 2){
                        acumB += v[nivel];
                    }
                    dosSubconjuntosAux(v, vObjetivo, nivel + 1, acumA, acumB, exito);

                    if (!exito.getValor()){
                        if (c == 1){
                            acumA -= v[nivel];
                        }else if (c == 2){
                            acumB -= v[nivel];
                        }
                    }
                }
                c++;
            }
        }
    }

        // SESION 1.1
    public static boolean hayRepartoEquitativo(int[] bienes){
        Booleano exito = new Booleano(false);
        int suma = 0, num;
        for (int i = 0; i<bienes.length;i++){
            suma += bienes[i];
        }
        if (suma % 3 == 0){
            num = suma / 3;
            hayRepartoEquitativoAux(bienes, new int[3], num, 0, exito);
        }

        return exito.getValor();
    }
    private static void hayRepartoEquitativoAux(int[] bienes, int[] suma, int num, int nivel, Booleano exito){
        if (nivel == bienes.length){
            if ((suma[0] == num) && (suma[1] == num) && (suma[2] == num))
                exito.setValor(true);
        }else {
            int c = 0;
            while (!exito.getValor() && c < 3){
                if (suma[c] + bienes[nivel] <= num) {
                    suma[c] += bienes[nivel];

                    hayRepartoEquitativoAux(bienes, suma, num, nivel+1, exito);
                    if (!exito.getValor()){
                        suma[c] -= bienes[nivel];
                    }
                }
                c++;
            }
        }


    }

    // SELECCION OPTIMA
        // EJERCICIO MOCHILA
    private static void mochilaOptimaAux(int[] pesos, int[] valores, int maxPeso, int nivel, int[] solucion, int valorActual, int pesoActual, int[] mejorSolucion, Entero valorMejor){
        if (nivel == pesos.length){
            if (valorActual > valorMejor.getValor()){
                valorMejor.setValor(valorActual);
                for (int i = 0; i< solucion.length; i++){
                    mejorSolucion[i] = solucion[i];
                }
            }
        }else {
            for (int c = 0; c < 2; c++){

                if (c == 0 || pesoActual + pesos[nivel] <= maxPeso) {
                    solucion[nivel] = c;
                    pesoActual += (pesos[nivel] * c);
                    valorActual += (valores[nivel] * c);

                    mochilaOptimaAux(pesos, valores, maxPeso, nivel + 1, solucion, valorActual, pesoActual, mejorSolucion, valorMejor);

                    solucion[nivel] = 0;
                    pesoActual -= (pesos[nivel] * c);
                    valorActual -= (valores[nivel] * c);
                }
            }
        }
    }
    public static int[] mochilaOptima(int[] pesos, int[] valores, int maxPeso){
        Entero valorMejor = new Entero(0);
        int[] mejorSolucion = new int[pesos.length];

        mochilaOptimaAux(pesos, valores, maxPeso, 0, new int[pesos.length], 0, 0, mejorSolucion, valorMejor);

        return mejorSolucion;
    }

        // EJERCICIO MONEDA
    public static int[] monedaOptima(int[] valores, int cambio){
        Entero valorMejor = new Entero(Integer.MAX_VALUE);
        int[] mejorSolucion = new int[valores.length];
        int[] solucion = new int[valores.length];
        monedaOptimaAux(valores, cambio, 0, solucion, mejorSolucion, 0, 0, valorMejor);

        return mejorSolucion;
    }
    private static void monedaOptimaAux(int[] valores, int cambio, int nivel, int[] solucion,int[] mejorSolucion, int valorActual, int monedaActual, Entero valorMejor){
        if (valorActual == cambio){
            if (monedaActual < valorMejor.getValor()){
                valorMejor.setValor(monedaActual);
                for (int i = 0; i < solucion.length; i++){
                    mejorSolucion[i] = solucion[i];
                }
            }
        }else if(nivel < valores.length){
            for(int c = 0; c <= cambio / valores[nivel]; c++){
                if (c == 0 || valorActual + valores[nivel] <= cambio){
                    solucion[nivel] = c;
                    valorActual += (valores[nivel] * c);
                    monedaActual += c;
                    if (monedaActual < valorMejor.getValor()){
                        monedaOptimaAux(valores, cambio, nivel + 1, solucion, mejorSolucion, valorActual, monedaActual, valorMejor);
                    }
                    solucion[nivel] = 0;
                    monedaActual -= c;
                    valorActual -= (valores[nivel] * c);
                }
            }
        }






    }

        // SESION 1.4 - 2 veces
    public static boolean[]subcSuma0MaxElem (int[] v){
        boolean[] mejorSolucion = new boolean[v.length];
        boolean[] solucion = new boolean[v.length];
        for (int i = 0; i < v.length; i++){
            mejorSolucion[i] = false;
            solucion[i] = false;
        }
        Entero valor2 = new Entero(0);
        int suma = 0;
        int nivel = 0;
        int numUsado = 0;
        subcSumaOMaxElemAux(v, nivel, mejorSolucion, solucion, suma, valor2, numUsado);

        return mejorSolucion;
    }
    private static void subcSumaOMaxElemAux(int[] v, int nivel, boolean[] mejorSolucion, boolean[] solucion, int suma, Entero valor2, int numUsado){
        if (nivel == v.length){
            if (suma == 0 && numUsado > valor2.getValor()){
                valor2.setValor(numUsado);
                for (int i = 0; i < solucion.length; i++){
                    mejorSolucion[i] = solucion[i];
                }
            }
        }else {
            for (int c = 0; c < 2;c++){
                if (c == 1){
                    solucion[nivel] = true;
                    numUsado++;
                }
                suma += (v[nivel] * c);

                subcSumaOMaxElemAux(v, nivel + 1, mejorSolucion, solucion, suma, valor2, numUsado);

                if (c == 1){
                    solucion[nivel] = false;
                    numUsado--;
                }

                suma -= (v[nivel] * c);
            }
        }

    }

        // SESION 1.3 -
    public static boolean[] maxUsoRecurso(int[] comienzo, int[] fin){
        boolean[] mejorSolucion = new boolean[comienzo.length];
        boolean[] solucion = new boolean[comienzo.length];
        Entero mejorRango = new Entero(0);
        for (int i = 0; i < comienzo.length; i++){
            mejorSolucion[i] = false;
            solucion[i] = false;
        }
        maxUsoRecursoAux(comienzo, fin, solucion, mejorSolucion, 0, 0, mejorRango);

        return mejorSolucion;
    }
    private static void maxUsoRecursoAux(int[] comienzo, int[] fin, boolean[] solucion, boolean[] mejorSolucion, int rango, int nivel,Entero mejorRango){
        if (nivel == comienzo.length){
            if (rango > mejorRango.getValor()){
                mejorRango.setValor(rango);
                for (int i = 0; i < mejorSolucion.length; i++){
                    mejorSolucion[i] = solucion[i];
                }
            }
        }else {
            for (int c = 0; c < 2; c++){
                if ((c == 0) || aceptable(comienzo, fin, solucion, nivel)) {
                    solucion[nivel] = (c == 1);
                    rango += (fin[nivel] - comienzo[nivel]) * c;

                    maxUsoRecursoAux(comienzo, fin, solucion, mejorSolucion, rango, nivel + 1, mejorRango);

                    rango -= (fin[nivel] - comienzo[nivel]) * c;
                    solucion[nivel] = false;
                }
            }
        }
    }
    private static boolean aceptable(int[] comienzo, int[] fin, boolean[] solucion, int nivel){
        boolean resul = true;
        int i = 0;

        while (resul && i<nivel){
            if (solucion[i]){
                resul = comienzo[i] >= fin[nivel] || comienzo[nivel] >= fin[i];
            }
            i++;
        }

        return resul;
    }

}
