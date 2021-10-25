#include <stdio.h>
int main(int argc, char const *argv[])
{
    // work in progress zadanko takze narazie jeszcze nie jest dokonczone
    int a, b, c;
    printf("podaj pierwsza wartosc calkowita: ");
    scanf("%d", &a);
    printf("podaj druga wartosc calkowita: ");
    scanf("%d", &b);
    printf("podaj trzecia wartosc calkowita: ");
    scanf("%d", &c);
    if (a > b)
    {
        if(a>=c)
            printf("wieksza wartosc to %d\n", a);
        
    }
    else if (b > a)
    {
        if(b>=c)
            printf("wieksza wartosc to %d\n", b);
    }
    else if (c > a)
    {
        if(c>=b)
            printf("wieksza wartosc to %d\n", c);
    }
    else 
    {
        printf("Wszystkie liczby sa rowne");
    }
    return 0;
}
