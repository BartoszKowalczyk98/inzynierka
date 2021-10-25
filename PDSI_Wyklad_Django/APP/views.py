from django.forms import modelformset_factory, modelform_factory
from django.shortcuts import render, get_object_or_404, redirect

from .models import *


# from .forms import pozycja_zakupowa_form


# Create your views here.

def index(request):
    form = modelform_factory(Pozycja_Do_Kupienia, fields='__all__', exclude=('bought',))
    czydodane = False
    if request.method == 'POST':
        formset = form(request.POST, request.FILES)
        formset.save()
        czydodane = True

    return render(request, 'APP/index.html', {'czydodane': czydodane})


def lista_zakupow(request):
    lista_pozycji = Pozycja_Do_Kupienia.objects.order_by('-shop', )
    lista_sklepow_do_odwiedzenia = []
    lista_sklepow_i_sum = []

    class szybka_innerclassa():
        shop = None
        sum = 0.0

        def __init__(self, shoppens, sumka) -> None:
            super().__init__()
            self.shop = shoppens
            self.sum = sumka

        def __eq__(self, o: object) -> bool:
            return self.shop.__eq__(o)

    for p in lista_pozycji:
        if not lista_sklepow_do_odwiedzenia.__contains__(p.shop):
            lista_sklepow_do_odwiedzenia.append(p.shop)

    for s in lista_sklepow_do_odwiedzenia:
        cos = szybka_innerclassa(s, 0.0)
        for p in lista_pozycji:
            if p.shop == s:
                cos.sum += round(float(p.price_per_unit * p.amount), 2)
        lista_sklepow_i_sum.append(cos)
    lista_sklepow_i_sum.reverse()

    context = {
        'lista_pozycji': lista_pozycji,
        'lista_sklepow_do_odwiedzenia': lista_sklepow_do_odwiedzenia,
        'lista_sklepow_i_sum': lista_sklepow_i_sum,
    }
    return render(request, 'APP/lista_zakupow.html', context)


def dodawanie_pozycji_zakupowej(request):
    form = modelform_factory(Pozycja_Do_Kupienia, fields='__all__', exclude=('bought',))
    context = {
        'formset': form,
    }
    return render(request, 'APP/dodawanie_pozycji_zakupowych.html', context)


def kupione(request, pk):
    form = get_object_or_404(Pozycja_Do_Kupienia, pk=pk)
    if request.method == 'POST':
        form.bought = True
        form.save()
        return lista_zakupow(request)
    return lista_zakupow(request)
