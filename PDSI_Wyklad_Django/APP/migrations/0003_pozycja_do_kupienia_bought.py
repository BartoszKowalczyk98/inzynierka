# Generated by Django 3.0.5 on 2020-04-22 18:20

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('APP', '0002_auto_20200422_1005'),
    ]

    operations = [
        migrations.AddField(
            model_name='pozycja_do_kupienia',
            name='bought',
            field=models.BooleanField(default=False, editable=False),
        ),
    ]