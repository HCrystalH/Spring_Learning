# Code Review Summary: ASM6 (CourseShare)

## Overview
Spring Boot 4.1.0 (Java 21) course-sharing platform. Course catalog, category tag cloud, student reviews with approval workflow, instructor dashboard for CRUD and moderation.

---

## Progress Update

| Issue | Before | Now |
|-------|--------|-----|
| View names missing subdirectory prefix | ❌ 9 broken | ✅ All fixed with proper `public/` / `instructor/` prefix |
| `category-list` template missing | ❌ Returned nonexistent view | ✅ Uses `public/categories` |
| Login/logout endpoints missing | ❌ Removed | ✅ Restored with `InstructorService` |
| `fragments/head` → `fragments/header` | ❌ 5 wrong | ✅ All 9 templates use `fragments/header` |
| `review.courseTitle` nonexistent property | ❌ | ✅ `${review.course.title}` |
| Status badges empty | ❌ | ✅ "DRAFT" / "PUBLISHED" / "ARCHIVED" text |
| `footer.html` namespace | ❌ Missing `.org` | ✅ `https://www.thymeleaf.org` |
| Stray `a` in CSS | ❌ | ✅ Fixed |
| Native queries table name | ❌ | ✅ Fixed (JPQL) |

---

## Remaining Issues

### 1. `InstructorController.loginForm()` returns `"login"` instead of `"instructor/login"` (`InstructorController.java:39`)
Template is at `templates/instructor/login.html`, but view name is just `"login"`. Thymeleaf will look for `templates/login.html` → **404**.

### 2. `InstructorController.login()` redirects to `"/login"` on failure (`InstructorController.java:57`)
```java
return "redirect:/login";
```
This redirects to GET `/login` which doesn't map to any controller (the login endpoint is at `/instructor/login`). Should be `"redirect:/instructor/login"`.

### 3. `nav-instructor.html` links to `/auth/logout` instead of `/instructor/logout` (`fragments/nav-instructor.html:14`)
```html
<a href="/auth/logout" class="btn-logout"> Logout</a>
```
The logout endpoint is mapped at `InstructorController.logout()` via `@GetMapping("/logout")` on a controller with `@RequestMapping("/instructor")` → actual path is `/instructor/logout`. Link will **404**.

---

## Summary

| Severity | Count |
|----------|-------|
| 🔴 Critical | 3 |
| 🟡 Moderate | 0 |
| 🔵 Minor | 0 |
| **Total** | **3** |

All 3 remaining issues are in the instructor auth flow. Everything else looks clean.

opencode -s ses_0e49f3118ffeWqh4COqkuh8P6M