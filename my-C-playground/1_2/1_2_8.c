#include<stdio.h>
int main(int argc, char const *argv[])
{
    int a,b,c;
    printf("podaj 3 liczby oddzielone od siebie spacjami: ");
    scanf("%d %d %d",&a,&b,&c);
    float srednia = 0.0;
    srednia += (float)a;
    srednia += (float)b;
    srednia += (float)c;
    srednia/=3.0;
    printf("srednia arytmetyczna z podnych liczb to: %f\n",srednia);

    return 0;
}
