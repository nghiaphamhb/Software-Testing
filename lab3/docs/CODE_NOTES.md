# Code Notes

Ghi chú ngắn để nhìn lại code Selenium trong project này.

## 1. `Duration` và timeout

`Duration` biểu diễn một khoảng thời gian trong Java:

```java
Duration.ofSeconds(10)
Duration.ofMillis(500)
Duration.ZERO
```

Trong Selenium hiện tại, các timeout nhận `Duration`.

Các loại timeout đang dùng:

- `IMPLICIT_WAIT`: thời gian chờ ngầm khi `findElement()` tìm phần tử.
- `EXPLICIT_WAIT`: thời gian chờ điều kiện cụ thể với `WebDriverWait`.
- `PAGE_LOAD_TIMEOUT`: thời gian tối đa chờ `driver.get(...)` load xong.
- `SCRIPT_TIMEOUT`: thời gian tối đa chờ `executeAsyncScript(...)`.

Ghi nhớ:

- `implicit wait = 0` giúp dễ kiểm soát hơn.
- `explicit wait` thường an toàn hơn cho web động.

## 2. `driver`, `wait`, `js`

Ba biến quan trọng trong `SeleniumConfigurator`:

```java
private final WebDriver driver;
private final WebDriverWait wait;
private final JavascriptExecutor js;
```

Ý nghĩa:

- `driver`: điều khiển browser.
- `wait`: đợi điều kiện đúng rồi mới chạy tiếp.
- `js`: chạy JavaScript trực tiếp khi cần.

Quan hệ:

- `driver` là core.
- `wait` và `js` được tạo ra từ `driver`.

## 3. `ChromeOptions` và `PageLoadStrategy`

```java
ChromeOptions options = new ChromeOptions();
options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
```

Ý nghĩa:

- `ChromeOptions`: nơi cấu hình Chrome trước khi tạo `ChromeDriver`.
- `PageLoadStrategy.NORMAL`: Selenium chờ trang load đầy đủ rồi mới trả quyền điều khiển cho code tiếp theo.

Ba mức thường gặp:

- `NORMAL`: chờ đầy đủ.
- `EAGER`: chờ DOM sẵn sàng.
- `NONE`: gần như không chờ.

## 4. `WebElement`, `@FindBy`, `PageFactory`

### `WebElement`

Đại diện cho một element HTML trên trang.

Ví dụ:

```java
WebElement loginButton = driver.findElement(...);
loginButton.click();
```

### `@FindBy`

Annotation dùng để khai báo locator cho field trong page object.

Ví dụ:

```java
@FindBy(xpath = "//input[@name='login']")
private WebElement usernameInput;
```

### `PageFactory`

Dùng để khởi tạo các field có `@FindBy` trong page class.

Tư duy ngắn:

- `WebElement` = phần tử thật để thao tác
- `@FindBy` = cách tìm phần tử
- `PageFactory` = kích hoạt các khai báo đó

## 5. XPath trong project

Theo đề bài, locator nên ưu tiên XPath thay vì dựa trực tiếp vào ID.

Nên dùng:

```xpath
//input[@name='login']
//button[normalize-space()='Sign in']
//div[contains(@class,'item')]
```

Không nên dùng XPath tuyệt đối:

```xpath
/html/body/div[2]/form/input
```

Ưu tiên:

- XPath tương đối
- `contains(...)`
- `normalize-space()`
- attribute ổn định hơn class/id động

## 6. Wait trong code test

Nguyên tắc ngắn:

- Không nên lạm dụng `implicit wait`.
- Dùng `WebDriverWait` khi cần đợi:
  - element visible
  - element clickable
  - URL đổi
  - text xuất hiện

Mục tiêu là tránh flaky test do timing.

## 7. Allure annotation

Ví dụ:

```java
@Epic("GitHub UI functional testing")
@Feature("Authentication")
@Story("UC-01: Authentication")
```

Ý nghĩa:

- `@Epic`: nhóm lớn
- `@Feature`: nhóm chức năng
- `@Story`: mapping với use case

Chúng chỉ là metadata cho report, không đổi logic test.

## 8. Ghi nhớ nhanh

- `driver` điều khiển browser
- `wait` giúp test ổn định
- `js` là đường phụ khi cần can thiệp DOM
- `Duration` dùng cho timeout
- `ChromeOptions` cấu hình browser trước khi chạy
- `@FindBy` + `PageFactory` phục vụ Page Object Model
- XPath nên tương đối, ngắn, ổn định
