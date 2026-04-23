# Test Scenarios Cho Bộ Kiểm Thử GitHub

Tài liệu này mô tả các scenario kiểm thử đang được hiện thực trong `demo/src/test/java`, nhưng được nhóm lại theo **use case lớn** để tránh chia quá nhỏ.

## Quy Ước

| Trường | Ý nghĩa |
| --- | --- |
| Scenario ID | Mã kịch bản test |
| Use case | Use case tương ứng trong `USE_CASE.md` |
| Test file | File test hiện thực |
| Test method | Method JUnit 5 hiện thực scenario |
| Preconditions | Điều kiện trước khi chạy |
| Expected result | Kết quả mong đợi |

---

## 1. UC-01 Authentication

**Test file:** `AuthenticationTest.java`  

## TS-AUTH-01. Open Login Page From Landing Page

**Test method:** `openLoginPageFromLandingTest`
**Preconditions:** Browser ở trạng thái chưa đăng nhập.

**Steps:**
1. Mở GitHub landing page.
2. Kiểm tra URL bắt đầu bằng `https://github.com`.
3. Kiểm tra nút **Sign in** hiển thị.
4. Click **Sign in**.
5. Kiểm tra URL chứa `login`.
6. Kiểm tra username input và password input.

**Expected result:** Người dùng mở được login page từ landing page.

---

## TS-AUTH-02. Invalid Login

**Use case:** UC-01 Authentication  
**Test file:** `AuthenticationTest.java`  
**Test method:** `invalidLoginTest`

**Preconditions:** Browser đang ở trang login.

**Steps:**
1. Nhập username giả.
2. Nhập password giả.
3. Submit form.
4. Đọc error message.
5. Kiểm tra URL session.

**Expected result:** GitHub từ chối đăng nhập và hiển thị thông báo lỗi phù hợp.

---

## TS-AUTH-03. Valid Login

**Test method:** `validLoginTest`
**Preconditions:** `.env` có `GITHUB_USERNAME` và `GITHUB_PASSWORD`.

**Steps:**
1. Đọc credential từ `.env`.
2. Login bằng credential hợp lệ.
3. Kiểm tra user menu hiển thị.

**Expected result:** Người dùng đăng nhập thành công.

**Risk:** Có thể fail do 2FA, captcha hoặc xác minh thiết bị.

---

## TS-AUTH-04. Sign Out

**Test method:** `logOut`
**Preconditions:** Người dùng đã đăng nhập.

**Steps:**
1. Mở user menu.
2. Click **Sign out**.
3. Xác nhận sign out.
4. Kiểm tra nút **Sign in** hiển thị lại.

**Expected result:** Người dùng trở về trạng thái chưa đăng nhập.

---

## 2. UC-02 Find GitHub Content

**Test file:** `SearchTest.java`  

## TS-SEARCH-01. Search Repository By Keyword

**Test method:** `searchRepositoryByKeywordTest`
**Preconditions:** Keyword được cấu hình trong `.env`.

**Steps:**
1. Mở landing page.
2. Thực hiện search bằng keyword.
3. Kiểm tra URL chứa query.

**Expected result:** GitHub hiển thị trang kết quả cho keyword đã nhập.

---

## TS-SEARCH-02. Open Best Search Result

**Test method:** `openBestSearchResult`

**Preconditions:** Đã có kết quả search ở bước trước.

**Steps:**
1. Click kết quả đầu tiên.
2. Kiểm tra URL điều hướng.

**Expected result:** Browser mở đúng trang kết quả mục tiêu.

---

## 3. UC-03 Checkout Public Repository

**Test file:** `RepositoryTest.java`  

## TS-REPO-01. Open Public Repository

**Test method:** `openPublicRepositoryTest`

**Preconditions:** `.env` có `TARGET_USERNAME` và `TARGET_REPO`.

**Steps:**
1. Mở repository public.
2. Kiểm tra URL chứa repo name.
3. Kiểm tra tên repo hiển thị.

**Expected result:** Repository public mở đúng owner/repo mong đợi.

---

## TS-REPO-02. Open Repository Issues Tab

**Test method:** `openRepositoryIssuesTabTest`

**Preconditions:** Đang ở repository public.

**Steps:**
1. Click tab **Issues**.
2. Kiểm tra URL chứa `issues`.

**Expected result:** Tab Issues mở đúng.

---

## TS-REPO-03. Open Repository Pull Requests Tab

**Test method:** `openRepositoryPullRequestsTabTest`

**Preconditions:** Đang ở repository public hoặc tab repository hợp lệ.

**Steps:**
1. Click tab **Pull requests**.
2. Kiểm tra URL chứa `pulls`.

**Expected result:** Tab Pull requests mở đúng.

---

## 4. UC-04 Star/Unstar Public Repository

**Test file:** `RepositoryTest.java` 

## TS-REPO-04. Star Repository
 
**Test method:** `starRepositoryTest`

**Preconditions:** Có credential hợp lệ.

**Steps:**
1. Mở repository.
2. Click Star khi chưa đăng nhập.
3. Login.
4. Kiểm tra quay lại repository.
5. Click **Star**.
6. Kiểm tra trạng thái **Starred**.

**Expected result:** Repository được star thành công.

---

## TS-REPO-05. Unstar Repository

**Test method:** `unstarRepositoryTest`

**Preconditions:** Repository đang ở trạng thái starred.

**Steps:**
1. Kiểm tra đang ở đúng repository.
2. Kiểm tra user đã login.
3. Kiểm tra **Starred** hiển thị.
4. Click **Starred**.
5. Kiểm tra **Star** hiển thị.

**Expected result:** Repository được unstar để cleanup trạng thái.

---

## 5. UC-05 Edit Own Profile

**Test file:** `ProfileTest.java`  

## TS-PROFILE-01. Open Own Profile

**Test method:** `openOwnProfileFromUserMenuTest`

**Steps:**
1. Login bằng credential hợp lệ.
2. Mở user menu.
3. Mở profile cá nhân.
4. Kiểm tra nút **Edit profile**.

**Expected result:** Profile cá nhân mở đúng và sẵn sàng cho chỉnh sửa.

---

## TS-PROFILE-02. Edit Profile Bio

**Test method:** `editProfileBioTest`

**Preconditions:** Đang ở own profile.

**Steps:**
1. Kiểm tra trạng thái trang profile.
2. Lưu bio hiện tại.
3. Cập nhật bio mới.
4. Kiểm tra bio mới hiển thị.
5. Khôi phục bio cũ.
6. Kiểm tra bio được restore.

**Expected result:** Bio được cập nhật và khôi phục thành công.

---

## 6. UC-06 Follow/Unfollow Other User

**Test file:** `UserFollowTest.java`  

## TS-USER-01. Open Public User Profile

**Test method:** `openPublicUserProfileTest`

**Steps:**
1. Mở profile public.
2. Kiểm tra URL chứa target username.
3. Kiểm tra tên profile hiển thị.

**Expected result:** Profile public mở đúng.

---

## TS-USER-02. Follow And Unfollow User

**Test method:** `followAndUnfollowUserTest`

**Preconditions:** Có credential hợp lệ.

**Steps:**
1. Click Follow khi chưa login để đến login.
2. Login bằng credential hợp lệ.
3. Kiểm tra quay lại target profile.
4. Follow user.
5. Kiểm tra nút **Unfollow**.
6. Unfollow user.
7. Kiểm tra nút **Follow**.

**Expected result:** Follow/unfollow hoạt động đúng và trạng thái được cleanup.
