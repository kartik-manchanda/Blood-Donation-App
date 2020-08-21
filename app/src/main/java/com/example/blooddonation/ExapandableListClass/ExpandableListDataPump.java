package com.example.blooddonation.ExapandableListClass;

import android.content.Context;

import com.example.blooddonation.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

        public static HashMap<String, List<String>> getData() {
            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

            List<String> faq1 = new ArrayList<String>();
            faq1.add("Blood is a body fluid in humans and other animals that delivers necessary substances such as nutrients and oxygen to the cells and transports metabolic waste products away from those same cells. In vertebrates, it is composed of blood cells suspended in blood plasma.");

            List<String> faq2 = new ArrayList<String>();
            faq2.add("You have to donate blood yourself to find out. By Blood donation you greatly contribute towards a healthier, happier society by making available safe blood. Any one may need blood at any time may be ourselves or our near  ones. You also benefit in other ways by donating blood as it reduces the chances of ischemic heart diseases.");

            List<String> faq3 = new ArrayList<String>();
            faq3.add("There are no side effects of blood donation. The Blood bank staff ensures that your blood donation is a good experience as far as possible to enable you to become a repeat and a regular blood donor. There are a number of people who have donated   more tha25–100 times in their life time.");

            List<String> faq4 = new ArrayList<String>();
            faq4.add("The eligible donors  should be  between 18 to 60 years of age, having a healthy pattern of lifestyle  The body weight should be above 45 kgs.  The haemoglobin should be above 12.5 g%.");

            List<String> faq5 = new ArrayList<String>();
            faq5.add("As only sterile disposables are used to collect  blood and these disposables can be used only one time it eliminates any chances of acquiring any disease from blood donation.");

            List<String> faq6 = new ArrayList<String>();
            faq6.add("After every three –four months you can donate blood.");

            List<String> faq7 = new ArrayList<String>();
            faq7.add("Anything that you normally eat at home., Eat a  light snacks and a cold drink  before blood donation  is sufficient.");

            List<String> faq8 = new ArrayList<String>();
            faq8.add("You will be asked to fill a donor form. Your medical history will be taken by a medical personnel and  a small drop of blood to check blood group as well as  Hb for anemia. After  simple medical screening process and being found medically fit to donate, you will be sent to blood collection  room. The actual blood collection from the donor takes about 7–10 minutes followed by a little rest and refreshments.");

            List<String> faq9 = new ArrayList<String>();
            faq9.add("Your blood in the blood bank is processed and within 6 hours of Blood collection It  is separated into components viz. Red Cells, White Cells, Plasma &amp; Platelets. These Blood components are made available for the patients. By making blood components, all the useful parts of blood can be used and from one unit of blood four patients can be benefited.");

            List<String> faq10 = new ArrayList<String>();
            faq10.add("A unit is about 450 ml of donated blood. The average adult has between four and five litres of blood in his or her body, and can easily spare one unit.");

            expandableListDetail.put("What is blood ?", faq1);
            expandableListDetail.put("Why should I donate blood ?", faq2);
            expandableListDetail.put("Are their any side effects of Blood donations ?", faq3);
            expandableListDetail.put("What are the eligibility criteria of blood donation ?", faq4);
            expandableListDetail.put("Can I get any disease like AIDS or Hepatitis or any other disease by Blood donation ?", faq5);
            expandableListDetail.put("How often can I donate Blood ?", faq6);
            expandableListDetail.put("What should I eat before blood-donation ?", faq7);
            expandableListDetail.put("How much  time does it take to donate blood ?", faq8);
            expandableListDetail.put("What happens to blood I donate ?", faq9);
            expandableListDetail.put("What is a “unit” of blood ?", faq10);

            return expandableListDetail;
        }
    }

