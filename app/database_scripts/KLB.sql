-- knoldus_leaderboard schema

-- !Ups

CREATE DATABASE knoldus_leaderboard;
\c knoldus_leaderboard
CREATE TABLE knolder(id SERIAL PRIMARY KEY NOT NULL, full_name VARCHAR(100) NOT NULL, wordpress_id VARCHAR(100) UNIQUE, email_id VARCHAR(100) UNIQUE);
CREATE TABLE blog(id INT PRIMARY KEY NOT NULL, wordpress_id VARCHAR(100) NOT NULL, published_on TIMESTAMP NOT NULL, title VARCHAR(255) NOT NULL);
CREATE TABLE all_time_reputation(id SERIAL PRIMARY KEY NOT NULL, knolder_id INT REFERENCES knolder(id), full_name VARCHAR(100) NOT NULL, score INT NOT NULL, rank INT NOT NULL);
CREATE TABLE monthly_reputation(id SERIAL PRIMARY KEY NOT NULL, knolder_id INT REFERENCES knolder(id), full_name VARCHAR(100) NOT NULL, score INT NOT NULL, rank INT NOT NULL);
CREATE TABLE quarterly_reputation(id SERIAL PRIMARY KEY NOT NULL, knolder_id INT REFERENCES knolder(id), full_name VARCHAR(100) NOT NULL, streak VARCHAR(100) NOT NULL);
insert into knolder(full_name,wordpress_id)values('Sidharth Khattri','sidharthkhattri');
insert into knolder(full_name,wordpress_id)values('Manish Kumar Mishra','knoldermanish');
insert into knolder(full_name,wordpress_id)values('Narayan Kumar','narayan343');
insert into knolder(full_name,wordpress_id)values('Pranjut Protim Gogoi','pranjut');
insert into knolder(full_name,wordpress_id)values('Gaurav Kumar Shukla','srigaurav');
insert into knolder(full_name,wordpress_id)values('Ayush Kumar Mishra','ayushmishra2005');
insert into knolder(full_name,wordpress_id)values('Mayank Bairagi','mayankbairagi');
insert into knolder(full_name,wordpress_id)values('Rishi Khandelwal','khandelwalrishi');
insert into knolder(full_name,wordpress_id)values('Sahil Sawhney','lihas55');
insert into knolder(full_name,wordpress_id)values('Himani Arora','himaniarora1');
insert into knolder(full_name,wordpress_id)values('Prabhat Kashyap','prabhatkashyap92');
insert into knolder(full_name,wordpress_id)values('Shubham Verma','bondhitman');
insert into knolder(full_name,wordpress_id)values('Randhir kumar','randhir1910');
insert into knolder(full_name,wordpress_id)values('Ayush Hooda','ayushhooda14');
insert into knolder(full_name,wordpress_id)values('Azmat Hasan','azmathasan92');
insert into knolder(full_name,wordpress_id)values('Megha Jatana','m3ghajatana');
insert into knolder(full_name,wordpress_id)values('Divyansh Jain','divyanshjain837');
insert into knolder(full_name,wordpress_id)values('Niharika Datta','niharika76');
insert into knolder(full_name,wordpress_id)values('Ruchika Dubey','ruchika0524');
insert into knolder(full_name,wordpress_id)values('Himanshu Gupta','himanshuknolder');
insert into knolder(full_name,wordpress_id)values('Vikas Hazrati','knoldus');
insert into knolder(full_name,wordpress_id)values('Deepak Mehra','deepak028');
insert into knolder(full_name,wordpress_id)values('Sarfaraz Hussain','sarfarazhussain211');
insert into knolder(full_name,wordpress_id)values('Upanshu Chaudhary','upanshu21');
insert into knolder(full_name,wordpress_id)values('Muskan Gupta','muskangupta3006');
insert into knolder(full_name,wordpress_id)values('Sarvesh Tripathi','knoldersarvesh');
insert into knolder(full_name,wordpress_id)values('Shubham Goyal','shubhamgoyal01');
insert into knolder(full_name,wordpress_id)values('Girish Chandra Bharti','girishbharti');
insert into knolder(full_name,wordpress_id)values('Ramandeep','ramandeepblogs');
insert into knolder(full_name,wordpress_id)values('kundan kumar','kundankumarr');
insert into knolder(full_name,wordpress_id)values('Jasmine Kaur','jasminekaurtech');
insert into knolder(full_name,wordpress_id)values('Vandana Yadav','vandana759');
insert into knolder(full_name,wordpress_id)values('Alok Jha','alokjha9495');
insert into knolder(full_name,wordpress_id)values('Swantika Gupta','swantikag');
insert into knolder(full_name,wordpress_id)values('Pankaj Chaudhary','pankajchaudhary5');
insert into knolder(full_name,wordpress_id)values('Mukesh Kumar','mukeshkumarqa');
insert into knolder(full_name,wordpress_id)values('Komal Rajpal','komalrajpal24');
insert into knolder(full_name,wordpress_id)values('Nishchal Vashisht','Nishchal26');
insert into knolder(full_name,wordpress_id)values('Utkarsha Musmade','utkarsha11');
insert into knolder(full_name,wordpress_id)values('Krishna Singh','krishnasingh9926');
insert into knolder(full_name,wordpress_id)values('Pawan Bisht','bishtpawan');
insert into knolder(full_name,wordpress_id)values('Neha Bhardwaj','nehabhardwaj01');
insert into knolder(full_name,wordpress_id)values('Mukesh Yadav','mukesh8wpress');
insert into knolder(full_name,wordpress_id)values('Etash','etash2901');
insert into knolder(full_name,wordpress_id)values('Shivraj Singh','shivrajsingh8179');
insert into knolder(full_name,wordpress_id)values('Vinisha Sharma','vinishasharma11');
insert into knolder(full_name,wordpress_id)values('Ayush Prashar','ayushprashar');
insert into knolder(full_name,wordpress_id)values('Bhawna Sharma','bhawna94');
insert into knolder(full_name,wordpress_id)values('Jyoti Jali','jyotijaliknoldus');
insert into knolder(full_name,wordpress_id)values('Sanjana Aggarwal','sanjanaaggarwal5121');
insert into knolder(full_name,wordpress_id)values('Munander Singh','munandersingh');
insert into knolder(full_name,wordpress_id)values('Harshit Daga','dagaharshit');
insert into knolder(full_name,wordpress_id)values('Ankur Thakur','ankurthakur25');
insert into knolder(full_name,wordpress_id)values('Prashant Sharma','prashantsharma12');
insert into knolder(full_name,wordpress_id)values('Nitin Arora','nitinarora1519');
insert into knolder(full_name,wordpress_id)values('Gautam Singal','gautamsingal');
insert into knolder(full_name,wordpress_id)values('Manas Kashyap','manaskashyap');
insert into knolder(full_name,wordpress_id)values('Rudar','damanrudar-gmail.com');
insert into knolder(full_name,wordpress_id)values('Lokesh Aggarwal','lokeshaggarwal090297');
insert into knolder(full_name,wordpress_id)values('Juoko Virtanen','joukovirtanen');
insert into knolder(full_name,wordpress_id)values('Yatharth Sharma','Yatharthsharma4251');
insert into knolder(full_name,wordpress_id)values('Bhavya Aggarwal','bhavyaknoldus');
insert into knolder(full_name,wordpress_id)values('Ram Indukuri','ramindukuri');
insert into knolder(full_name,wordpress_id)values('Anmol Sarna','anmolsarna09');
insert into knolder(full_name,wordpress_id)values('Kaushik Nath','mytechnosphere');
insert into knolder(full_name,wordpress_id)values('Ayushi Hasija','ayushi24041992');
insert into knolder(full_name,wordpress_id)values('Praful Bangar','praful72');
insert into knolder(full_name,wordpress_id)values('Sakshi Gawande','sakshigawande12');
insert into knolder(full_name,wordpress_id)values('Rachna Bulbule','rachanabulbule');
insert into knolder(full_name,wordpress_id)values('Anjali Sharma','anjalisharma96');
insert into knolder(full_name,wordpress_id)values('Swapnil Gosavi','swapnilgosavi555');
insert into knolder(full_name,wordpress_id)values('Pinku Swargiary','pinkuswargiary');
insert into knolder(full_name,wordpress_id)values('Shubham Girdhar','girdharshubham');
insert into knolder(full_name,wordpress_id)values('Jashan Goyal','jashangoyal09');
insert into knolder(full_name,wordpress_id)values('Priyanka Thakur','priyankathakur090418');
insert into knolder(full_name,wordpress_id)values('Piyush Rana','bigtechnologies');
insert into knolder(full_name,wordpress_id)values('Nancy Jain','jainnancy17');
insert into knolder(full_name,wordpress_id)values('Anuj Saxena','anuj1207');
insert into knolder(full_name,wordpress_id)values('Neelam Lakra','neellakra21');
insert into knolder(full_name,wordpress_id)values('Jyoti Sachdeva','jyotisachdeva57');
insert into knolder(full_name,wordpress_id)values('Mudit Chhabra','muditchhabra6125');
insert into knolder(full_name,wordpress_id)values('Ayush Singhal','ayushsinghal0728');
insert into knolder(full_name,wordpress_id)values('Shivangi Gupta','shivangi1015');
insert into knolder(full_name,wordpress_id)values('Vidisha Gupta','vidisha41');
insert into knolder(full_name,wordpress_id)values('Miral Gandhi','miralgandhi5');
insert into knolder(full_name,wordpress_id)values('Paramjeet Yadav','@paramj94');
insert into knolder(full_name,wordpress_id)values('Shubhrank Rastogi','srknoldus');
insert into knolder(full_name,wordpress_id)values('Siddhant','sidnt2');
insert into knolder(full_name,wordpress_id)values('Sugandha Arora','@sugandhaaa');
insert into knolder(full_name,wordpress_id)values('Sachin Slathia','slathiasachin13');
insert into knolder(full_name,wordpress_id)values('Vinay Kumar','vinaykumar2310');
insert into knolder(full_name,wordpress_id)values('Rajeev Thakur','shiv219');
insert into knolder(full_name,wordpress_id)values('Nitin Aggarwal','technologystalkerblog');
insert into knolder(full_name,wordpress_id)values('Ayush Aggarwal','ayushaggarwalsite');
insert into knolder(full_name,wordpress_id)values('Jatin Dhawan','techtonic96484652');
insert into knolder(full_name,wordpress_id)values('Ayush Tiwari','ecstatictechie');
insert into knolder(full_name,wordpress_id)values('Shubham Agarwal','shubhamtechblog');
insert into knolder(full_name,wordpress_id)values('Deepankar','deepankarofficial');
insert into knolder(full_name,wordpress_id)values('Joseph Ross','rossjoseph246');
insert into knolder(full_name,wordpress_id)values('JustinB','justinbeile');
insert into knolder(full_name,wordpress_id)values('Rachel Jones','jrachel098');
insert into knolder(full_name,wordpress_id)values('Mansi Babbar','mansibabbar1998');

-- !Downs

DROP DATABASE knoldus_leaderboard;
