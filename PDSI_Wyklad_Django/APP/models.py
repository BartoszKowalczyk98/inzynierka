from django.contrib.auth.models import User
from django.core.validators import MinValueValidator
from django.db import models


# Create your models here.
class Shop(models.Model):
    shop_name = models.CharField(max_length=200)

    class Meta:
        ordering = ('-shop_name',)

    def __str__(self):
        return self.shop_name


class Product(models.Model):
    product_name = models.CharField(max_length=200)

    class Meta:
        ordering = ('-product_name',)

    def __str__(self):
        return self.product_name


class Pozycja_Do_Kupienia(models.Model):
    product_name = models.ForeignKey(Product, related_name='Product.product_name+', on_delete=models.CASCADE)
    amount = models.FloatField(default=0, validators=[MinValueValidator(0)])
    price_per_unit = models.FloatField(default=0.0, validators=[MinValueValidator(0)])
    shop = models.ForeignKey(Shop, related_name='Shop.shop_name+', on_delete=models.DO_NOTHING)
    bought = models.BooleanField(default=False)

    class Meta:
        ordering = ('shop',)

    def __str__(self):
        return (int(
            self.amount)).__str__() + " x " + self.product_name.product_name + " po " + self.price_per_unit.__str__() + "zł sztuka w " + self.shop.__str__() + " " + self.bought.__str__()
