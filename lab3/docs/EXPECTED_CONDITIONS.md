# Tóm gọn phần **`WebDriverWait` tập trung vào `ExpectedConditions`** như này:

## Cách dùng

```java
wait.until(ExpectedConditions....);
```

Ý nghĩa:

* `wait` = chờ
* `until(...)` = chờ tới khi điều kiện đúng
* `ExpectedConditions` = tập các điều kiện có sẵn để Selenium kiểm tra

---

## Mấy `ExpectedConditions` hay dùng nhất

### 1. `visibilityOf(...)`

Chờ **element hiện ra**

```java
wait.until(ExpectedConditions.visibilityOf(element));
```

Hoặc:

```java
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("...")));
```

Dùng khi:

* cần element xuất hiện rồi mới thao tác / đọc text

---

### 2. `elementToBeClickable(...)`

Chờ **element click được**

```java
wait.until(ExpectedConditions.elementToBeClickable(element));
```

Hoặc:

```java
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("...")));
```

Dùng khi:

* nút có rồi nhưng chưa bấm được

---

### 3. `presenceOfElementLocated(...)`

Chờ **element có mặt trong DOM**

```java
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("...")));
```

Khác với `visibility`:

* `presence` = có trong DOM, chưa chắc nhìn thấy
* `visibility` = có và nhìn thấy được

---

### 4. `invisibilityOf(...)`

Chờ **element biến mất**

```java
wait.until(ExpectedConditions.invisibilityOf(element));
```

Hoặc:

```java
wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("...")));
```

Dùng khi:

* chờ loading spinner mất đi
* chờ popup đóng

---

### 5. `textToBePresentInElement(...)`

Chờ **text xuất hiện trong element**

```java
wait.until(ExpectedConditions.textToBePresentInElement(element, "Profile"));
```

Hoặc:

```java
wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("..."), "Profile"));
```

Dùng khi:

* chờ message lỗi/thành công
* chờ text thay đổi

---

### 6. `urlContains(...)`

Chờ **URL chứa chuỗi nào đó**

```java
wait.until(ExpectedConditions.urlContains("dashboard"));
```

Dùng khi:

* login xong redirect sang trang khác

---

### 7. `urlToBe(...)`

Chờ **URL đúng chính xác**

```java
wait.until(ExpectedConditions.urlToBe("https://github.com/settings/profile"));
```

---

### 8. `titleContains(...)`

Chờ **title của trang chứa chuỗi**

```java
wait.until(ExpectedConditions.titleContains("GitHub"));
```

---

### 9. `frameToBeAvailableAndSwitchToIt(...)`

Chờ **frame sẵn sàng rồi switch vào**

```java
wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frameName"));
```

---

### 10. `alertIsPresent()`

Chờ **alert xuất hiện**

```java
wait.until(ExpectedConditions.alertIsPresent());
```

---

# Cái cần nhớ nhất

## Nếu muốn thao tác element:

* `visibilityOf`
* `elementToBeClickable`

## Nếu muốn kiểm tra element có tồn tại:

* `presenceOfElementLocated`

## Nếu muốn chờ biến mất:

* `invisibilityOf`

## Nếu muốn chờ trang đổi trạng thái:

* `urlContains`
* `urlToBe`
* `titleContains`

## Nếu muốn chờ nội dung đổi:

* `textToBePresentInElement`
