package com.lakhalifi.GestionDeStock.interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.util.StringUtils;


//je n'ai pas besoin de cette intercepteur pour ajouter l'attribut de l'entreprise à la requete, il se fait automatiquement
//je ne sais pas comment!!
public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql){
        //on verifie tout d'abord si la requête n'est pas vide et après s'elle s'agit d'une requête "select"
        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")){

            if(sql.contains("where")){
                //sql= sql+" and identreprise=1"; //problème: recherche avec code ne fonctionne pas
            }else{
                //sql= sql+" where identreprise=1";
            }

        }

        return super.onPrepareStatement(sql);
    }

}
