# Generated by Django 3.0.5 on 2020-04-21 12:36

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Product',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('product_name', models.CharField(max_length=200)),
            ],
            options={
                'ordering': ('-product_name',),
            },
        ),
        migrations.CreateModel(
            name='Shop',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('shop_name', models.CharField(max_length=200)),
            ],
            options={
                'ordering': ('-shop_name',),
            },
        ),
        migrations.CreateModel(
            name='Pozycja_Do_Kupienia',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('amount', models.FloatField(default=0)),
                ('price_per_unit', models.FloatField(default=0.0)),
                ('product_name', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='Product.product_name+', to='APP.Product')),
                ('shop', models.ForeignKey(on_delete=django.db.models.deletion.DO_NOTHING, related_name='Shop.shop_name+', to='APP.Shop')),
            ],
            options={
                'ordering': ('shop',),
            },
        ),
    ]
