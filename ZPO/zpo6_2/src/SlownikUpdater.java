import java.util.List;
import java.util.Map;

public  abstract  class SlownikUpdater {
    public static void updatePrzezGet(Map<String,Integer> mapaDoUpdate, List<String> listaSlow){
        for(String s : listaSlow){
            if(mapaDoUpdate.get(s) != null){
                mapaDoUpdate.put(s,mapaDoUpdate.get(s)+1);
            }
            else
                mapaDoUpdate.put(s,1);
        }
    }
    public  static void updatePrzezContainskey(Map<String,Integer> mapaDoUpdate,List<String> listaSlow){
        for(String s : listaSlow){
            if(mapaDoUpdate.containsKey(s)){
                mapaDoUpdate.put(s,mapaDoUpdate.get(s)+1);
            }
            else
                mapaDoUpdate.put(s,1);
        }
    }
    public static void updatePrzezgetOrDefault(Map<String,Integer> mapaDoUpdate,List<String> listaSlow){
        for(String s : listaSlow){
            if(mapaDoUpdate.getOrDefault(s,-1)==-1){
                mapaDoUpdate.put(s,1);
            }
            else
                mapaDoUpdate.put(s,mapaDoUpdate.get(s)+1);
        }
    }
    public static void updateprzezputIfAbsent(Map<String,Integer> mapaDoUpdate,List<String> listaSlow){
        for(String s : listaSlow){
            if(mapaDoUpdate.putIfAbsent(s,1)!=null){
                mapaDoUpdate.put(s,mapaDoUpdate.get(s)+1);
            }
        }
    }
}
