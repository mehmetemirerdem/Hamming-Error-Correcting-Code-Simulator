
package bilgmimarisproje2deneme2;

import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

public class BilgmimarisProje2deneme2 extends JFrame implements ActionListener {

    JFrame jfr;
    JLabel jlb, jlb1, jlb2, jlb3, jlb4, jlb5;
    JTextField jtf1, jtf2, jtf3, jtf4, jtf5;
    JRadioButton jrbt1, jrbt2, jrbt3;
    
    BilgmimarisProje2deneme2(){
        super("Hamming code calc");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        
        
        jlb = new JLabel("Data bit sayısını seçiniz : ");
        jlb.setBounds(200,50,150,20);
        this.add(jlb);
        
        jfr = new JFrame();
        jrbt1 = new JRadioButton("4 Bit");
        jrbt2 = new JRadioButton("8 Bit");
        jrbt3 = new JRadioButton("16 Bit");
        jrbt1.setBounds(450,25,100,30);    
        jrbt2.setBounds(450,50,100,30);   
        jrbt3.setBounds(450,75,100,30);    
        
        ButtonGroup bg=new ButtonGroup();
        bg.add(jrbt1);
        bg.add(jrbt2);
        bg.add(jrbt3);
        jrbt1.addActionListener(this);
        jrbt2.addActionListener(this);
        jrbt3.addActionListener(this);
        this.add(jrbt1);
        this.add(jrbt2);
        this.add(jrbt3);
        
        jlb1 = new JLabel("Data bits array : ");
        jlb1.setBounds(215, 125, 120, 30);
        this.add(jlb1);
        
        jtf1 = new JTextField();
        jtf1.setBounds(450,130,300,20);
        this.add(jtf1);
        
        jlb2 = new JLabel("check bits array : ");
        jlb2.setBounds(215, 175, 120, 30);
        this.add(jlb2);
        
        jtf2 = new JTextField();
        jtf2.setBounds(450,180,300,20);
        this.add(jtf2);
        
        jlb3 = new JLabel("Hamming word: ");
        jlb3.setBounds(215, 225, 120, 30);
        this.add(jlb3);
        
        jtf3 = new JTextField();
        jtf3.setBounds(450,230,300,20);
        this.add(jtf3);
        
        jlb4 = new JLabel("Hatalı word : ");
        jlb4.setBounds(215, 275, 120, 30);
        this.add(jlb4);
        
        jtf4 = new JTextField();
        jtf4.setBounds(450,280,300,20);
        this.add(jtf4);
        
        jlb5 = new JLabel("Hata konum : ");
        jlb5.setBounds(215, 325, 120, 30);
        this.add(jlb5);
        
        jtf5 = new JTextField();
        jtf5.setBounds(450,330,300,20);
        this.add(jtf5);
        
        
        
        this.setLayout(null);
        this.setSize(900, 620);
        this.setVisible(true);
        
    }
    
    static int controlBitCalc(Integer[] veribitleri, int kacbit){
        //kaç kontrol biti gerektiğini hesaplar
        int kontrolbit = 0;
        
        for(int i=0;i<kacbit;i++){
            int a;
            a = (int)Math.pow(2, i) - 1 - i;
            int karsılastırma = Integer.compare(a, kacbit);
            if(karsılastırma == 1 || karsılastırma == 0){
                kontrolbit = i;
                break;
            }
        }
        return kontrolbit;
    }
    
    static void reverse(Integer array[]){
        // arrayi ters çevirir
        Collections.reverse(Arrays.asList(array));
    }
    
    static Integer[] rastgele(int kacbit){
        // rastgele 0,1 lerden oluşan array üretir
        Random random = new Random();
        Integer[] asilarray = new Integer[kacbit];
        for(int i=0; i<kacbit;i++){
            asilarray[i] = random.nextInt(2);
        }
        return asilarray;
    }
    
    //lokasyonları tutan dizi fonk
    static Integer[] lokasyon(Integer[] dizi){
        Integer[] dizi2 = new Integer[dizi.length];
        int j=0;
        for(int i=0; i<dizi2.length; i++){
            if(dizi[i] == 1){
                dizi2[j++] = i+1;
            }
            else{
                dizi2[j++] = 0;
            }
        }
        return dizi2;
    }
    
    static int exor(Integer[] dizi2){
        // arraydeki 1 olan değerlerin önceden konumunu aldıktan sonra xor işlemine tabi tutar
        int exor = 0;
        for(int i=0; i<dizi2.length; i++){
            if(dizi2[i] != 0){
                exor = exor ^ dizi2[i];
            }
        }
        return exor;
    }
    
    public static void main(String[] args) {
        
        new BilgmimarisProje2deneme2();
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Integer[] a, b;
        int c;
        if(jrbt1.isSelected()){
            a = rastgele(4);
            reverse(a);
            jtf1.setText(Arrays.toString(a));
            b = lokasyon(a);
            c = exor(b);
            
            String cevirbit = Integer.toBinaryString(c);
            String[] cevirbitarr = cevirbit.split("");

            int checkbitvalue = controlBitCalc(a, 4);
            
            Integer[] bitarr = new Integer[checkbitvalue];
            for(int i = 0; i<bitarr.length; i++){
                bitarr[i] = 0;
            }

            for(int i=0; i< cevirbitarr.length; i++){
                bitarr[i] = Integer.parseInt(cevirbitarr[i]);
            }
            reverse(bitarr);
            jtf2.setText(Arrays.toString(bitarr));
            
            Integer[] sonarray = new Integer[b.length+bitarr.length];
                
            for(int i=0; i<sonarray.length; i++){
                sonarray[i] = 0;
            }
            
            sonarray[0] = bitarr[0];
            sonarray[1] = bitarr[1];
            sonarray[3] = bitarr[2];
            sonarray[2] = a[0];
                
            for(int i = 0; i<3; i++){
                sonarray[i+4] = a[i+1];
            }
            
            jtf3.setText(Arrays.toString(sonarray));
            
            Integer[] sonarrayb = sonarray.clone();
                
            Random rn = new Random();
                
            int secilen;
            secilen = rn.nextInt(7);
                
            if(sonarray[secilen] == 1){
                    
                sonarray[secilen] = 0;

            }
            else if(sonarray[secilen] == 0){
                    
                sonarray[secilen] = 1;

            }
            
            jtf4.setText(Arrays.toString(sonarray));
            
            int bayrak = 0;
                
            for(int i=0; i<7; i++){
                if(sonarray[i] != sonarrayb[i]){
                    bayrak = i;
                }
            }
            
            int sonexor;
                
            sonexor = (c^c)^(bayrak);
            
            jtf5.setText(Integer.toString(sonexor));
        }
        
        if(jrbt2.isSelected()){
            a = rastgele(8);
            reverse(a);
            jtf1.setText(Arrays.toString(a));
            b = lokasyon(a);
            c = exor(b);
            
            String cevirbit = Integer.toBinaryString(c);
            String[] cevirbitarr = cevirbit.split("");

            int checkbitvalue = controlBitCalc(a, 8);
            
            Integer[] bitarr = new Integer[checkbitvalue];
            for(int i = 0; i<bitarr.length; i++){
                bitarr[i] = 0;
            }

            for(int i=0; i< cevirbitarr.length; i++){
                bitarr[i] = Integer.parseInt(cevirbitarr[i]);
            }
            reverse(bitarr);
            jtf2.setText(Arrays.toString(bitarr));
            
            Integer[] sonarray = new Integer[b.length+bitarr.length];
                
            for(int i=0; i<sonarray.length; i++){
                sonarray[i] = 0;
            }
            
            sonarray[0] = bitarr[0];
            sonarray[1] = bitarr[1];
            sonarray[3] = bitarr[2];
            sonarray[2] = a[0];
            sonarray[7] = bitarr[3];
            sonarray[4] = a[1];
            sonarray[5] = a[2];
            sonarray[6] = a[3];

            for(int i = 0; i<4; i++){
                sonarray[i+8] = a[i+4];
            }
            
            jtf3.setText(Arrays.toString(sonarray));
            
            Integer[] sonarrayb = sonarray.clone();
                
            Random rn = new Random();
                
            int secilen;
            secilen = rn.nextInt(12);
                
            if(sonarray[secilen] == 1){
                    
                sonarray[secilen] = 0;

            }
            else if(sonarray[secilen] == 0){
                    
                sonarray[secilen] = 1;

            }
            
            jtf4.setText(Arrays.toString(sonarray));
            
            int bayrak = 0;
                
            for(int i=0; i<12; i++){
                if(sonarray[i] != sonarrayb[i]){
                    bayrak = i;
                }
            }
            
            int sonexor;
                
            sonexor = (c^c)^(bayrak);
            
            jtf5.setText(Integer.toString(sonexor));
        }
        
        if(jrbt3.isSelected()){
            a = rastgele(16);
            reverse(a);
            jtf1.setText(Arrays.toString(a));
            b = lokasyon(a);
            c = exor(b);
            
            String cevirbit = Integer.toBinaryString(c);
            String[] cevirbitarr = cevirbit.split("");

            int checkbitvalue = controlBitCalc(a, 16);
            
            Integer[] bitarr = new Integer[checkbitvalue];
            for(int i = 0; i<bitarr.length; i++){
                bitarr[i] = 0;
            }

            for(int i=0; i< cevirbitarr.length; i++){
                bitarr[i] = Integer.parseInt(cevirbitarr[i]);
            }
            reverse(bitarr);
            jtf2.setText(Arrays.toString(bitarr));
            
            Integer[] sonarray = new Integer[b.length+bitarr.length];
                
            for(int i=0; i<sonarray.length; i++){
                sonarray[i] = 0;
            }
            
            sonarray[0] = bitarr[0];
            sonarray[1] = bitarr[1];
            sonarray[3] = bitarr[2];
            sonarray[2] = a[0];
            sonarray[7] = bitarr[3];
            sonarray[4] = a[1];
            sonarray[5] = a[2];
            sonarray[6] = a[3];
            sonarray[8] = a[4];
            sonarray[9] = a[5];
            sonarray[10] = a[6];
            sonarray[11] = a[7];
            sonarray[12] = a[8];
            sonarray[13] = a[9];
            sonarray[14] = a[10];
            sonarray[15] = bitarr[4];
                
            for(int i = 0; i<5; i++){
                sonarray[i+16] = a[i+11];
            }
            
            jtf3.setText(Arrays.toString(sonarray));
            
            Integer[] sonarrayb = sonarray.clone();
                
            Random rn = new Random();
                
            int secilen;
            secilen = rn.nextInt(21);
                
            if(sonarray[secilen] == 1){
                    
                sonarray[secilen] = 0;

            }
            else if(sonarray[secilen] == 0){
                    
                sonarray[secilen] = 1;

            }
            
            jtf4.setText(Arrays.toString(sonarray));
            
            int bayrak = 0;
                
            for(int i=0; i<21; i++){
                if(sonarray[i] != sonarrayb[i]){
                    bayrak = i;
                }
            }
            
            int sonexor;
                
            sonexor = (c^c)^(bayrak);
            
            jtf5.setText(Integer.toString(sonexor));
        }
    }
    
}
