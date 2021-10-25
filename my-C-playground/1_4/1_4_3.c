#include <stdio.h>
int main(int argc, char const *argv[])
{
    int n,m,k;
    printf("podaj 3 liczby");
    scanf("%d %d %d",&n,&m, &k);
    int wielokrotnosc = n;
    while (wielokrotnosc <= k)
    {
        if(n>=m){
        printf("%d\n", wielokrotnosc);
        }
        wielokrotnosc*=n;
    }
    
    
    return 0;
}
