from .models import Pozycja_Do_Kupienia
from django.forms import ModelForm
class pozycja_zakupowa_form(ModelForm):
    class Meta:
        model = Pozycja_Do_Kupienia
        fields ='__all__'

