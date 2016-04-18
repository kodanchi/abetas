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
 * SelectCourseServlet is used by ajax to display list of PI related to the selected outcome
 */
@WebServlet(name = "SelectOutcomeServlet", urlPatterns = {"/SelectOutcomeServlet"})
public class SelectOutcomeServlet extends HttpServlet {
    private E_Select dbs;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        dbs = new E_Select();
        int tid = Integer.parseInt(request.getParameter("tid"));
        int pid = Integer.parseInt(request.getParameter("pid"));
        int oid = Integer.parseInt(request.getParameter("oid"));
        String oname = EncDec.getDecr(request.getParameter("oName"));
        try {

            PrintWriter out = response.getWriter();

            out.print("<h4>Outcome</h4>\n" +
                    "<div class=\"jumbotron\" >");
                out.print("<h7>"+oname+"</h7>");
            out.print("</div>");


            ArrayList<ArrayList<String>> pIListS = dbs.selectPIofOutcomeSummativeToEvaluate(tid, pid,oid);

            int threshold;
            int passPis = 0;
            int failPis = 0;
            ArrayList<String> PIRubrics = null;
            ArrayList<String> PIResults = null;
            float[] results = new float[]{0,0,0,0};

            PIRubrics = dbs.selectRubricsToEvaluate(tid);

            out.print("<div class=\"list-group well\">");
            for (ArrayList<String> PI : pIListS){

                PIResults = dbs.selectSummativeRubricResultsToEvaluate(Integer.valueOf(PI.get(0)),pid,tid);
                threshold = dbs.selectThresholdToEvaluate(Integer.valueOf(PI.get(0)));

                for (String sRubric : PIResults){
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


                float developed = results[2]!= 0 ? ( results[2] * 100 ) / PIResults.size() : 0;
                float exemplary = results[3]!= 0 ? ( results[3] * 100 ) / PIResults.size() : 0;
                float passOrFailresults = developed + exemplary;
                if(passOrFailresults > threshold ) {

                    out.print("<a target=\"_blank\" href=\"index.jsp?page=showGraph&id="+PI.get(0)+"&tid="+tid+"&pid="+pid+"&dataType=summative&piname="+
                            EncDec.getEncr(PI.get(1))+"\" class=\"list-group-item list-group-item-success\">"+PI.get(1)+
                            "</br><div class=\"pull-right\" >Summative | Thredshold: "+threshold+"%</div></br></a>");

                    passPis++;
                }else {

                    out.print("<a target=\"_blank\" href=\"index.jsp?page=showGraph&id="+PI.get(0)+"&tid="+tid+"&pid="+pid+"&dataType=summative&piname="+
                            EncDec.getEncr(PI.get(1))+"\" class=\"list-group-item list-group-item-danger\">"+PI.get(1)+
                            "</br><div class=\"pull-right\" >Summative | Thredshold: "+threshold+"%</div></br></a>");

                    failPis++;
                }
            }


            ArrayList<ArrayList<String>> pIListF = dbs.selectPIofOutcomeFormativeToEvaluate(tid, pid,oid);

            for (ArrayList<String> PI : pIListF){
                out.print("<a target=\"_blank\" href=\"index.jsp?page=showForm&id="+ EncDec.getEncr(PI.get(2)) + "&type="+EncDec.getEncr("formative")+
                        "\" class=\"list-group-item\">" + PI.get(1) + "</br><div class=\"pull-right\" >Formative</div></br></a>");
            }
            out.print("</div>");
            out.print("<div  class=\"jumbotron\">");
            out.print(String.format("Passed Performance Indicators: %s out of %s",passPis,pIListS.size()));
            out.print("</div>");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
