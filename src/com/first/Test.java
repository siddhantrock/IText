package com.first;


import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DottedBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class Test 
{

    public Test() 
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args) throws Exception
    {
        String str="report.pdf";
        PdfWriter writer=new PdfWriter(str);
        PdfDocument pdfDoc=new PdfDocument(writer);
        
        Document document = new Document(pdfDoc);
        
        float cloumn[]={150f,150f,150f,220f};
        Table table=new Table(cloumn);
        
        Cell source=new Cell();
        source.add(new Paragraph("Source").setBold());
        source.setTextAlignment(TextAlignment.CENTER);
        source.setBackgroundColor(Color.convertCmykToRgb(DeviceCmyk.CYAN), .10f);         
        table.addCell(source);
        
        Cell destination=new Cell();
        destination.add(new Paragraph("Destination").setBold());
        destination.setTextAlignment(TextAlignment.CENTER);
        destination.setBackgroundColor(Color.convertCmykToRgb(DeviceCmyk.CYAN), .10f);         
        table.addCell(destination);
        
        Cell message=new Cell();
        message.add(new Paragraph("Message").setBold());
        message.setTextAlignment(TextAlignment.CENTER);
        message.setBackgroundColor(Color.convertCmykToRgb(DeviceCmyk.CYAN), .10f);         
        table.addCell(message);
        
        Cell date_time=new Cell();
        date_time.add(new Paragraph("Time").setBold());
        date_time.setTextAlignment(TextAlignment.CENTER);
        date_time.setBackgroundColor(Color.convertCmykToRgb(DeviceCmyk.CYAN), .10f);         
        table.addCell(date_time);
        
        try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/socketproject","root","siddhu1234@#"))
        {
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("select * from rohit");
            
            while(rs.next())
            {
                Cell source1=new Cell();
                source1.add(new Paragraph(rs.getString("source")));
                source1.setTextAlignment(TextAlignment.CENTER);        
                table.addCell(source1);
        
                Cell destination1=new Cell();
                destination1.add(new Paragraph(rs.getString("destination")));
                destination1.setTextAlignment(TextAlignment.CENTER);     
                table.addCell(destination1);
        
                Cell message1=new Cell();
                message1.add(new Paragraph(rs.getString("message")));
                message1.setTextAlignment(TextAlignment.CENTER);  
                table.addCell(message1);
        
                Cell date_time1=new Cell();
                date_time1.add(new Paragraph(rs.getString("time")));
                date_time1.setTextAlignment(TextAlignment.CENTER);   
                table.addCell(date_time1);
                
            }
        }
        
        document.add(table);
        
        document.close();
        
        System.out.println("ok");
    }
}
