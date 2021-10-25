#include <stdio.h>
int main(int argc, char const *argv[])
{
    int a, b;
    printf("podaj pierwsza wartosc calkowita: ");
    scanf("%d", &a);
    printf("podaj druga wartosc calkowita: ");
    scanf("%d", &b);
    if (a < b)
    {
        printf("wieksza wartosc to %d\n", b);
    }
    else if (a > b)
    {
        printf("wieksza wartosc to %d\n", a);
    }
    else
    {
        printf("wieksza wartosc to %d\n",a);
    }

    return 0;
}
