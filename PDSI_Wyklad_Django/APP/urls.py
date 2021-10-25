from django.conf.urls import url
from django.urls import path
from . import views

urlpatterns = [
    path('', views.index, name='index'),
    path('lista_zakupow/', views.lista_zakupow, name='lista_zakupow'),
    path('dodawanie/', views.dodawanie_pozycji_zakupowej, name='dodawanie_pozycji_zakupowej'),
    path('kupione/', views.kupione, name='kupione'),
    path('kupione/<int:pk>', views.kupione, name='kupione')

]
