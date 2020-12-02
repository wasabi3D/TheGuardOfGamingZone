package Components;

import java.util.ArrayList;
import Utilities.Entities.WMember;

public class RankingData{
    private ArrayList<WMember> members;
    private ArrayList<Integer> scores;

    public RankingData(){
        members = new ArrayList<>();
        scores = new ArrayList<>();
    }

    public int size() {
        return members.size();
    }

    public boolean isEmpty() {
        return members.isEmpty();
    }

    public boolean containsKey(WMember key) {
        for (WMember obj: members
             ) {
            if(isEqualMember(key,obj)){
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Integer value) {
        for (Integer obj: scores
        ) {
            if(obj.equals(value)){
                return true;
            }
        }
        return false;
    }

    public Integer get(WMember key) {
        for(int i = 0;i< members.size();i++){
            if(isEqualMember(key, members.get(i))){
                return scores.get(i);
            }
        }
        return null;
    }

    public int get(int num){
        return scores.get(num);
    }

    public WMember getKey(int num){
        return members.get(num);
    }

    public void put(WMember key, Integer value) {
        for(int i = 0;i< members.size();i++){

            if(isEqualMember(key, members.get(i))){
                scores.set(i,value);
                return;
            }
        }
        members.add(key);
        scores.add(value);

    }

    public void replaceKey(int num,WMember newKey){
        members.set(num,newKey);
    }

    public void replaceValue(int num,int newValue){
        scores.set(num,newValue);
    }

    public boolean remove(WMember key) {
        for(int i = 0;i< members.size();i++){
            if(isEqualMember(key, members.get(i))){
                members.remove(i);
                scores.remove(i);
                return true;
            }
        }
        return false;
    }

    public void clear() {
        members.clear();
        scores.clear();
    }

    public boolean isEqualMember(WMember m1,WMember m2){
        return m1.getIdLong() == m2.getIdLong();
    }

    public boolean checkDataAvailability(){
        if(members.size() == scores.size()){
            return true;
        }
        return false;
    }

    public int getRankingSize(){
        return members.size();
    }

    public RankingData getSorted(){
        if(this.checkDataAvailability()){
            int tmp = this.getRankingSize();

            WMember tmpm; int tmpi;
            for(int i = 0;i<tmp;i++){
                for(int o = 0;o<tmp-1;o++){
                    if(this.get(o) < this.get(o+1)){
                        tmpi = this.get(o+1);
                        tmpm = this.getKey(o+1);

                        this.replaceKey(o+1, this.getKey(o));
                        this.replaceValue(o+1, this.get(o));

                        this.replaceKey(o,tmpm);
                        this.replaceValue(o,tmpi);

                    }
                }
            }
            return this;

        }else{
            return null;
        }
    }
}
