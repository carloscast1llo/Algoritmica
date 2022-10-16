public class main {
    public static void main(String args[]) {
        int[] vector = new int[]{-1,0,2,3,10,12,-23,-24,-7}; // Sesion 1.1
        int[] vector2 = new int[]{1,1,4,5,5,7,7,8,8,9,9}; // Sesion 1.2
        int[] vector3 = new int[]{2,5,8,9,22,34,45,98,101}; //Examen 2022
        int[] vector4 = new int[]{4,7,23,34,45,56,67,78,89}; //Examen 2022
        int[] vector5 = new int[]{-4,-2,0,1,1,2,2,2,3}; //Examen 2021
        int[] vector6 = new int[]{-4,-2,0,1,1,5,5,5,5}; //Examen 2021
        int[] vector7 = new int[]{-5,-2,-9,-4,1,5,7,0,-3};//Examen 2020 Junio
        int[] vector8 = new int[]{8,-1,2,4,2,-2,1,0,1}; //Examen 2020 Junio
        int[] vector9 = new int[]{5,7,8,9,3,2,1,0,7}; //Examen 2020 Diciembre
        int[] vector10 = new int[]{1,2,2,3,3,4,0,0,1}; //Examen 2019
        int[] vector11 = new int[]{4,7,10,15,23,32,1,2,3}; //Examen 2018
        int[] vector12 = new int[]{-10,-2,0,3,79,19,28,30,42,55}; // Sesion 1.3

        System.out.println(sumaPositivos(vector));
        System.out.println(elementoSolitario2(vector2));
        System.out.println();
        System.out.println(parImpar(vector3));
        System.out.println(ejDyVsept(vector5, vector6));
        System.out.println(Check2pos(vector7, vector8, 9));
        System.out.println(maxArrayColina(vector9));
        System.out.println(elementoEspecial(vector10));
        System.out.println(minArrayRotado(vector11));
        System.out.println(elementoEspecial2(vector12));
    }

    // SESION 1.1 - 2 veces
    public static int sumaPositivos(int [] vector){
        return sumaPositivosAux(vector, 0, vector.length-1);
    }
    public static int sumaPositivosAux(int [] vector,int i0,int iN){
        if(i0 == iN){
            if(vector[i0] > 0) {
                return vector[i0];
            } else {
                return 0;
            }
        } else {
            int k = (i0 + iN)/2;
            int x = sumaPositivosAux(vector,i0,k);
            int y = sumaPositivosAux(vector,k + 1,iN);
            return x + y;
        }
    }

    // SESION 1.2 - 2 veces - Ver again
    public static int elementoSolitario(int [] vector2){
        return elementoSolitarioAux(vector2, 0, vector2.length-1);
    }
    public static int elementoSolitarioAux(int [] vector2, int i0, int iN){
        if (i0 == iN){
            return vector2[i0];
        }else {
            int k = (i0 + iN) / 2;
            if (vector2[k-1] == vector2[k]){
                if (((k-2) - i0 % 2) == 0){
                    return elementoSolitarioAux(vector2, i0, k-2);
                }else {
                    return elementoSolitarioAux(vector2, k+1, iN);
                }
            }else if (vector2[k] == vector2[k+1]){
                if((iN-k+2) % 2 == 0){
                    return elementoSolitarioAux(vector2, k+2, iN);
                }else {
                    return elementoSolitarioAux(vector2, i0, k-1);
                }
            }else{
                return vector2[k];
            }
        }
    }

    public static int elementoSolitario2(int [] vector2){
        return elementoSolitarioAux2(vector2, 0, vector2.length-1);
    }
    public static int elementoSolitarioAux2(int [] vector2, int i0, int iN){
        if (i0 == iN){
            return vector2[i0];
        }else {
            int k = (i0 + iN) / 2;
            if (vector2[k] == vector2[k+1]){
                if ((iN-k+2) % 2 == 0){
                    return elementoEspecialAux2(vector2, k+2, iN);
                }else {
                    return elementoEspecialAux2(vector2, i0, k-1);
                }
            }else if (vector2[k-1] == vector2[k]){
                if (((k-2) - i0 % 2) == 0){
                    return elementoEspecialAux2(vector2, i0, k-2);
                }else {
                    return elementoEspecialAux2(vector2, k+1, iN);
                }
            }else {
                return vector2[k];
            }
        }
    }

    // Examen 2022
    public static int parImpar(int [] vector3){
        return parImparAux(vector3, 0, vector3.length-1);
    }
    public static int parImparAux(int [] vector3, int i0, int iN){
        if (i0 == iN){
            return i0;
        }else {
            int k = (i0 + iN) / 2;
            if (esPar(k) && esPar(vector3[k]) || !esPar(k) && !esPar(vector3[k])){
                return parImparAux(vector3, k+1, iN);
            }else {
                return parImparAux(vector3, i0, k);
            }
        }
    }
    public static boolean esPar(int n){
        return n % 2 == 0;
    }

    // Examen 2021
    public static int ejDyVsept(int[] vector5, int[] vector6){
        return ejDyVseptAux(vector5, vector6, 0, vector5.length - 1);
    }
    public static int ejDyVseptAux(int[] vector5, int[] vector6, int i0, int iN){
        if (i0 == iN){
            if (vector5[i0] == vector6[i0]){
                return -1;
            }else {
                return i0;
            }
        }else {
            int k = (i0 + iN) / 2;
            if (vector5[k] == vector6[k]){
                return ejDyVseptAux(vector5, vector6, k+1, iN);
            }else {
                return ejDyVseptAux(vector5, vector6, i0, k);
            }
        }
    }

    // Examen Junio 2020 - 2 veces
    public static boolean Check2pos (int[] vector7, int[] vector8, int v){
        boolean stop = false, resultado=false;
        int i = 0;
        Mergesort.mergeSort(vector8);
        while (!stop){
            resultado = Check2posAux(vector7[i], vector8, 0, vector8.length-1, v);
            if(i==vector7.length-1 || resultado){
                stop = true;
            }
            i++;
        }
        return resultado;
    }
    public static boolean Check2posAux (int vector7, int[] vector8, int i0, int iN, int v){
        if (i0 == iN){
            return vector7 + vector8[i0] == v;
        }else {
            int k = (i0 + iN) / 2;
            if (vector7 + vector8[k] < v){
                return Check2posAux(vector7, vector8, k+1, iN, v);
            }else if(vector7 + vector8[k] > v){
                return Check2posAux(vector7, vector8, i0, k, v);
            }else {
                return true;
            }
        }
    }

    // Examen Diciembre 2020 - 2 veces
    public static int maxArrayColina(int[] vector9){
        return maxArrayColinaAux(vector9, 0, vector9.length-1);
    }
    public static int maxArrayColinaAux(int[] vector9, int i0, int iN){
        if (i0 == iN){
            return vector9[i0];
        }else if(vector9.length == 2){
            return Math.max(vector9[i0], vector9[iN]);
        } else{
            int k = (i0 + iN) / 2;
            if (vector9[k] > vector9[k+1] && vector9[k] > vector9[k-1]){
                return vector9[k];
            }else if(vector9[k] < vector9[k+1]){
                return maxArrayColinaAux(vector9, k+1, iN);
            }else{
                return maxArrayColinaAux(vector9, i0, k);
            }
        }
    }

    // Examen 2019
    public static int elementoEspecial(int[] vector10){
        if(vector10.length == 1){
            return vector10[0];
        }else{
            if (vector10[0] == vector10[vector10.length-1]){
                return elementoEspecialAux(vector10, 1, vector10.length-2);
            }else {
                return elementoEspecialAux(vector10, 0, vector10.length-1);
            }
        }
    }
    public static int elementoEspecialAux(int[] vector10, int i0, int iN){
        if (i0 == iN){
            return vector10[i0];
        }else {
            int k = (i0 + iN) / 2;
            if (vector10[k] == vector10[k+1]){
                if ((k-1-i0) % 2 == 0){
                    return elementoEspecialAux(vector10, i0, k-1);
                }else {
                    return elementoEspecialAux(vector10, k+2, iN);
                }
            }else if(vector10[k-1] == vector10[k]){
                if ((k-2-i0) % 2 == 0){
                    return elementoEspecialAux(vector10, i0, k-2);
                }else {
                    return elementoEspecialAux(vector10, k+1, iN);
                }
            }else {
                return vector10[k];
            }
        }
    }

    // Examen 2018
    public static int minArrayRotado(int[] vector11){
        if (vector11[0] < vector11[vector11.length-1]){
            return vector11[0];
        }else {
            return minArrayRotadoLuqueAux(vector11, 0, vector11.length-1);
        }
    }
    public static int minArrayRotadoAux(int[] vector11, int i0, int iN){
        if (i0 == iN){
            return vector11[i0];
        }else {
            int k = (i0 + iN) / 2;
            if (vector11[i0] > vector11[k]){
                return minArrayRotadoAux(vector11, i0, k);
            }else if(vector11[k] > vector11[iN]){
                return minArrayRotadoAux(vector11, k+1, iN);
            }else {
                return vector11[i0];
            }
        }
    }
    public static int minArrayRotadoLuqueAux(int[] vector11, int i0, int iN){
        if (i0 == iN){
            return vector11[i0];
        }else {
            int k = (i0 + iN) / 2;
            if (vector11[k] < vector11[k-1] && vector11[k] < vector11[k+1]){
                return vector11[k];
            }else if(vector11[0] > vector11[k]){
                return minArrayRotadoLuqueAux(vector11, i0, k);
            }else {
                return minArrayRotadoLuqueAux(vector11, k+1, iN);
            }
        }
    }

    //Sesion 1.3 - 2 veces
    public static int elementoEspecial2(int[] vector){
        return elementoEspecialAux2(vector, 0, vector.length-1);
    }
    public static int elementoEspecialAux2(int[] vector, int i0, int iN){
        if (i0 == iN){
            if (vector[i0] == i0){
                return i0;
            }else {
                return -1;
            }
        }else {
            int k = (i0 + iN) / 2;
            if (vector[k] > k){
                return elementoEspecialAux2(vector, i0, k);
            }else if(vector[k] < k){
                return elementoEspecialAux2(vector, k+1, iN);
            }else {
                return k;
            }
        }
    }


    //Sesion 1.4
    public static int longMaxSubArrayOrdenado(int[] vector){
        return longMaxSubArrayOrdenadoAux(vector, 0, vector.length-1);
    }
    public static int longMaxSubArrayOrdenadoAux(int[] vector, int i0, int iN){
        if (i0 == iN){
            return 1;
        }else {
            int k = (i0 + iN) / 2;
            int x = longMaxSubArrayOrdenadoAux(vector, i0, k);
            int y = longMaxSubArrayOrdenadoAux(vector, k+1, iN);
            int z = longMaxSubArrayOrdenadoCruzada(vector, i0, iN, k);
            return Math.max(x,Math.max(y,z));
        }
    }
    public static int longMaxSubArrayOrdenadoCruzada(int[] vector, int i0, int iN, int k){
        int i = k;
        while (i0 < i && vector[i-1] <= vector[i]){
            i--;
        }
        int j = k;
        while (j < iN && vector[j+1] >= vector[j]){
            j++;
        }

        return j-i+1;
    }

    // EJERCICIO PDF
    public static int maxSubArray(int[] vector, int i0, int iN){
        return maxSubArrayAux(vector, 0, vector.length-1);
    }
    public static int maxSubArrayAux(int[] vector, int i0, int iN){
        if (i0 == iN){
            return vector[i0];
        }else {
            int k = (i0 + iN) / 2;

            int x = maxSubArrayAux(vector, i0, k);
            int y = maxSubArrayAux(vector, k+1, iN);
            int z = maxSubArrayCruzada(vector, i0, iN, k);
            return Math.max(x, Math.max(y,z));
        }
    }
    public static int maxSubArrayCruzada(int[] vector, int i0, int iN, int k){
        int sumaMax = Integer.MIN_VALUE;
        int suma = 0;
        for(int i = k; i >= i0; i--){
            suma += vector[i];
            if (suma > sumaMax){
                sumaMax = suma;
            }
        }
        suma = sumaMax;
        for(int j = k; j <= iN; j--){
            suma += vector[j];
            if (suma > sumaMax){
                sumaMax = suma;
            }
        }

        return sumaMax;
    }

}
