package EDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Mojahed on 2/27/2016.
 */
@WebServlet(name = "SelectSectionServlet", urlPatterns = {"/SelectSectionServlet"})
public class SelectSectionServlet extends HttpServlet {
    private E_Select dbs;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/////////////////////////SelectSectionServlet ");
        response.setContentType("text/plain");
        dbs = new E_Select();
        int tid = Integer.parseInt(request.getParameter("tid"));
        int pid = Integer.parseInt(request.getParameter("pid"));
        int pIid = Integer.parseInt(request.getParameter("pIid"));
        int sid = Integer.parseInt(request.getParameter("sid"));
        String cid =request.getParameter("cid");
        try {
            PrintWriter out = response.getWriter();


            ArrayList<ArrayList<String>> SectionList = null;
            String evidence = null;
            int threshold;
            ArrayList<String> PIRubrics = null;
            ArrayList<String> PIResults = null;

            float[] results = new float[]{0,0,0,0};


            if(cid.equals("Overall")){


                System.out.print("Overall");

            }else {
                System.out.println("inside else");

                evidence = dbs.selectEvidenceOfSummativeToEvaluate(sid,pIid,tid,pid,cid);
                threshold = dbs.selectThresholdOfSummativeToEvaluate(sid,pIid,tid,pid,cid);
                PIRubrics = dbs.selectRubricsToEvaluate(tid);
                PIResults = dbs.selectSummativeRubricResultsOfSectionToEvaluate(sid,pIid,pid,tid,cid);




                for (String sRubric : PIResults){
                    System.out.println("SectionResults: "+sRubric);
                    if(sRubric.equals(PIRubrics.get(0))){
                        results[0]++;
                    }else if(sRubric.equals(PIRubrics.get(1))){
                        results[1]++;
                    }else if(sRubric.equals(PIRubrics.get(2))){
                        results[2]++;
                    }else if(sRubric.equals(PIRubrics.get(3))){
                        results[3]++;
                    }
                }


                out.print("<div class=\"col-md-7 \" >");
                float developed = results[2]!= 0 ? ( results[2] * 100 ) / PIResults.size() : 0;
                float exemplary = results[3]!= 0 ? ( results[3] * 100 ) / PIResults.size() : 0;
                float passOrFailresults = developed + exemplary;
                System.out.println("developed + exemplary = "+ passOrFailresults);
                if(passOrFailresults > threshold ) {
                    out.print(String.format("Results indicating this section fails below %s with %.2f",threshold+"%",
                            ( passOrFailresults - threshold)));
                }else {
                    out.print(String.format("Results indicating this section fails below %s with %.2f",threshold+"%",
                            (threshold - passOrFailresults)));
                }

                out.print("</div>");



                //out.print("<div class=\"row\">\n");
                if(evidence != null) {
                    out.print("<div class=\"col-md-4 pull-right\" >" +
                            "<a target=\"_blank\" class=\"btn btn-success btn-fill pull-right\" href=\""+evidence+"\" >Show Evidence</a>\n" +
                            "</div>");
                }else {
                    out.print("<div class=\"col-md-4 pull-right\" >" +
                            "No evidence were found\n" +
                            "</div>");
                }


                out.print("<script type=\"text/javascript\">\n" );

                out.print("var sResults = [\n");

                for (int i=0;i < PIRubrics.size();i++){
                    out.print(results[i]!= 0 ? ( results[i] * 100 ) / PIResults.size() : 0 );
                    if(i!=PIRubrics.size()-1) out.print(",\n");
                }

                out.print("];\n");
                out.print("popChart(\"Section: "+sid+"\",\"rgba(164, 102, 40, 0.8)\",sResults);\n");


                out.println("</script>\n");


            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
