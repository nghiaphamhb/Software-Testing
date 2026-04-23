# 🧪 Selenium & Functional Testing - Tóm tắt kiến thức

Vị trí: từ trang 73 đến trang 89

Tài liệu này là bản script ngắn để ôn và thuyết trình. Nó đã gộp luôn phần kiến thức nền quan trọng từ file `BASIC_KNOWLEDGE.md`.

---

# 1. Functional Testing (Kiểm thử chức năng)

## 📌 Khái niệm
- Functional testing là kiểm tra xem **phần mềm có hoạt động đúng theo yêu cầu hay không**.
- Là loại kiểm thử dựa trên **use case / luồng sử dụng**.
- Kiểm tra hệ thống từ góc nhìn **người dùng cuối**, thường tập trung vào UI và hành vi chức năng.

## ⚙️ Tập trung vào
- Input
- Output
- Hành vi của hệ thống
- So sánh **kết quả thực tế** với **expected result**

## 🎯 Mục tiêu
- Đảm bảo chức năng hoạt động đúng
- Phát hiện lỗi logic
- Kiểm tra luồng xử lý của người dùng

## 🧩 Các khái niệm hay bị hỏi
- **Use case**: mục tiêu sử dụng của người dùng
- **Test scenario**: kịch bản kiểm thử ở mức tổng quát
- **Test case**: trường hợp kiểm thử cụ thể
- **Checklist**: danh sách các mục cần xác nhận
- **Expected result**: kết quả mong đợi

## 🛠 Công cụ
- Open-source:
  - Selenium
  - Sahi
  - Watir
- Thương mại:
  - HP
  - IBM Rational

## 💡 Ví dụ ngắn
Chức năng đăng nhập:
- Nhập đúng → đăng nhập thành công
- Nhập sai → hiển thị lỗi

---

# 2. Selenium - Tổng quan

## 📌 Selenium là gì
- Bộ công cụ dùng để **tự động hóa kiểm thử web**
- Dùng để test:
  - UI
  - Luồng người dùng

## ⚙️ Thành phần chính
- Selenium IDE
- Selenium WebDriver
- Selenium Grid

## 🌍 Đặc điểm
- Hỗ trợ nhiều trình duyệt
- Hỗ trợ nhiều ngôn ngữ:
  - Java
  - Python
  - C#
  - PHP

## 🧠 Nguyên lý hoạt động cơ bản
Selenium:
1. Mở trình duyệt
2. Tìm phần tử trên trang
3. Thực hiện hành động:
   - click
   - nhập text
   - chọn dropdown
4. Kiểm tra kết quả

## ✅ Ưu điểm
- Tự động hóa test, tiết kiệm thời gian
- Chạy lặp lại nhiều lần
- Hỗ trợ cross-browser

---

# 3. Selenium IDE

## 📌 Khái niệm
- Công cụ ghi lại thao tác người dùng theo kiểu **record & playback**
- Thường dùng để tạo nhanh test template ban đầu

## ⚙️ Chức năng
- Ghi lại thao tác trên web
- Xuất thành script/code
- Có thể thêm command và assertion

## ⚠️ Hạn chế
- Logic test yếu
- Không phù hợp project lớn
- Khó kiểm thử UI động phức tạp

---

# 4. Các command cơ bản

## 🖱 Tương tác
- `click` / `clickAndWait`
- `type`
- `select`
- `open`

## 🧪 Kiểm tra
- `assert*` → sai thì dừng test
- `verify*` → sai nhưng test vẫn chạy tiếp

## ⏱ Đồng bộ
- `wait*` → chờ phần tử / trang load

## 🧰 Command khác
- `store` → lưu giá trị vào biến
- `echo` → in log
- `${var}` → dùng biến

---

# 5. Assertion vs Verification

## 🔍 Mục đích
Dùng để kiểm tra:
- Element có tồn tại không
- Text có đúng không
- Title, value, state có đúng không

## ⚠️ Khác nhau

| Loại | Khi sai |
| --- | --- |
| assert | Dừng test ngay |
| verify | Test vẫn tiếp tục |

## 🎯 Cách dùng
- `assert` → dùng cho điều kiện bắt buộc
- `verify` → dùng cho kiểm tra phụ

## Ví dụ verify phổ biến
- `verifyTextPresent`
- `verifyTitle`
- `verifyElementPresent`
- `verifyValue`

---

# 6. Wait / Đồng bộ

## 📌 Tại sao cần
- Trang có thể load chậm
- Element có thể render muộn
- Nếu không chờ đúng, test dễ fail ngẫu nhiên

## ⚙️ Ví dụ lệnh wait
- `waitForPageLoad`
- `waitForAlert`
- `waitForTable`
- `waitForTitle`

## Ghi nhớ nhanh
- Wait giúp giảm flaky test
- Không nên click/check ngay khi element chưa sẵn sàng

---

# 7. Selenium Server và luồng chạy test

## 📌 Vai trò
- Cho phép phối hợp chạy test với browser
- Trong kiến trúc cũ đóng vai trò trung gian giữa script và browser

## 🔄 Flow khái quát
1. Selenium IDE ghi lại thao tác
2. Convert thành script
3. Script gửi request tới Selenium Server / môi trường Selenium
4. WebDriver điều khiển browser

## 🌐 Browser hỗ trợ
- Chrome
- Firefox
- Safari
- Edge
- IE

![1](../../assets/1.png)

---

# 8. Quy trình phát triển test

## 🧪 Các bước
1. Xác định use case
2. Tạo checklist và test scenarios
3. Viết test bằng Java
4. Tạo project trong IDE
5. Thêm thư viện:
   - JUnit / TestNG
   - Selenium
6. Import hoặc viết test code
7. Chạy test và phân tích kết quả

---

# 9. XPath Locator

## 📌 XPath là gì
- XPath là ngôn ngữ dùng để **tìm phần tử trong HTML DOM**
- Rất quan trọng khi element được sinh động và không ổn định

## 📌 Vấn đề cần giải quyết
- ID có thể thay đổi
- Class có thể dài hoặc động
- DOM của website hiện đại thường phức tạp

## 🧩 Giải pháp
- Ưu tiên XPath tương đối
- Tránh phụ thuộc vào absolute path
- Theo đề bài, ưu tiên chọn bằng XPath thay vì dựa vào ID

## 🧪 Ví dụ

Theo tag:
```xpath
//input
```

Theo attribute:
```xpath
//input[@name='email']
```

Theo text:
```xpath
//button[text()='Login']
```

Theo `contains()`:
```xpath
//button[contains(text(),'Log')]
```

Theo `starts-with()`:
```xpath
//input[starts-with(@id,'user')]
```

## ⚠️ Tuyệt đối vs tương đối

Không nên:
```xpath
/html/body/div[2]/form/input
```

Nên dùng:
```xpath
//form//input[@type='text']
```

## Làm việc với nhiều phần tử
```xpath
//div[@class='item']
```

Phần tử thứ nhất:
```xpath
(//div[@class='item'])[1]
```

---

# 10. Selenium Grid

![2](../../assets/2.png)

## 📌 Selenium Grid là gì
Selenium Grid cho phép:
- Chạy test song song
- Chạy test trên nhiều browser và nhiều OS

## 🎯 Mục đích
- Giảm thời gian chạy test
- Kiểm tra cross-browser
- Kiểm tra trên nhiều môi trường
- Scale hệ thống test

## ⚙️ Thành phần chính
- **Client**: nơi chạy test script
- **Router**: nhận request
- **Distributor**: chọn node phù hợp
- **Node**: nơi thực thi test
- **Session Map**: lưu session
- **Event Bus**: đồng bộ giữa các thành phần

## 🔄 Cách hoạt động
1. Test script gửi request
2. Router nhận request
3. Distributor chọn node
4. Node chạy test trên browser
5. Kết quả trả về client

## 🚀 Ưu điểm
- Chạy song song
- Test nhiều môi trường
- Dễ mở rộng
- Phù hợp CI/CD

## ⚠️ Nhược điểm
- Cấu hình phức tạp hơn
- Tốn tài nguyên hơn
- Debug khó hơn local

## Khi nào nên dùng
- Có nhiều test case
- Cần test nhiều browser
- Có pipeline CI/CD

## Ghi nhớ nhanh
- Grid = **Parallel + Distributed Testing**
- Node = nơi chạy test
- Client = nơi viết test
- Distributor = điều phối

---

# 11. Ý chính để trả lời khi bảo vệ

## ✔ Functional Testing
- Kiểm tra chức năng, không phải hiệu năng
- Dựa trên use case
- So sánh actual với expected

## ✔ Selenium
- Tự động hóa thao tác người dùng trên web
- Có thể hỗ trợ nhiều browser
- WebDriver là thành phần chính để điều khiển browser

## ✔ XPath
- Dùng để tìm element trong DOM
- Hữu ích khi website có element động
- Ưu tiên XPath tương đối
- Tránh phụ thuộc vào ID nếu đề bài yêu cầu vậy
