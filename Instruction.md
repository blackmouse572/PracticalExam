### Requirements
- Use sessions to store the current User
- If you log in with the **Admin** role you can do it: `Create`/`Read`/`Update`/`Delete` data in table _ACCOUNT_ and
  _PRODUCT_ but can't delete my own account.
- If you log in with the **User** role you can do it: `Read`/`Update` data in table _ACCOUNT_ and `Read` data in
  table _PRODUCT_

### Specification

- If not login, you can't access to the page.
- If login with the **Admin** role, you can access to the page.
- If login with the **User** role, you can access to the page.
- 
