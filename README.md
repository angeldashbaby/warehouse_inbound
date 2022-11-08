# Angel Baby

> ระบบจัดการคลังสินค้าขนาดใหญ่สำหรับผู้ประกอบการ start up ที่ต้องการสร้างตัว มีระบบนำเข้าและนำออก, ประวัติสินค้าเข้าออก ทำงานในรูปแบบ microservice ที่มีความยิดหยุ่น
## Team Member
- [6310450051 ลีโอณิช เซ็ง](https://github.com/KenzieLeonic)
- [6310450484 ณภัทร พัชโรภาสววงศ์](https://github.com/aungpor)
- [6310450662 รัชต์ธร ทรงศรีวิสุทธิ์](https://github.com/Ratchathorn)
- [6310450671 รินลดา ตีระศิริชัย](https://github.com/opaller91)
- [6310451022 จิรัชญา พูลผล](https://github.com/ppinip)
- [6310451294 พชร สุวราวรนาถ](https://github.com/lrisia)

## Install and Run

ติดตั้งหรือ git clone โปรเจคทั้งหมดไปไว้ใน folder เดียวกัน <br>
จากนั้น start หรือ run ทุกโปรเจคยกเว้น data-warehose-front <br>
และ run คำสั่งตามนี้ใน folder data-warehose-front

```bash
docker-compose up -d
docker-compose exec app npm install
docker-compose exec app npm install moment
docker-compose exec app npm run dev
```

เข้าถึงด้วย port 8091 ด้วยลิงก์ [http://localhost:8091](http://localhost:8091) <br>
โหลดเอกสารอธิบายโปรเจคได้ที่นี่ > [project_description.pdf](https://github.com/angeldashbaby/data-warehose-front/files/9965213/Software_Engineer_Project-Slide.pdf)
