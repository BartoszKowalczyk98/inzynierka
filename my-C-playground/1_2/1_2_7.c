#include<stdio.h>
int main(int argc, char const *argv[])
{
    int liczba;
    printf("podaj liczbe calkowita: ");
    scanf("%d",&liczba);
    liczba++;
    printf("liczba o 1 wieksza od podanej to: %d\n",liczba);
    return 0;
}
