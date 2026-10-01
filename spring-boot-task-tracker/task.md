### Lab — "Task Tracker" REST API (Spring Boot web only, in-memory storage)

#### Goal

Practice the full Session 1 loop — starters, auto-configuration, the embedded server, the main class, `application.yml`, and `@Value` — with **zero persistence**. Storage is a `Map` inside the controller (or a small in-memory "store" class), same spirit as the `ProductController` example from the session.

#### Domain — one entity, plain Java, no annotations needed

**`Task`**

|Field|Type|Notes|
|---|---|---|
|`id`|`Long`|generated in-memory (e.g. `AtomicLong`)|
|`title`|`String`|required, not blank|
|`description`|`String`|optional|
|`completed`|`boolean`|default `false`|
|`dueDate`|`LocalDate`|optional|

#### Functional requirements — endpoints (unchanged)

| Method   | Path                        | Behavior                         | Status codes                     |
| -------- | --------------------------- | -------------------------------- | -------------------------------- |
| `POST`   | `/api/tasks`                | Create a task from the JSON body | `201` + `Location` header        |
| `GET`    | `/api/tasks`                | List all tasks                   | `200`                            |
| `GET`    | `/api/tasks?completed=true` | Filter by completion             | `200` (empty list if none match) |
| `GET`    | `/api/tasks/{id}`           | Fetch one task                   | `200`, or `404`                  |
| `PUT`    | `/api/tasks/{id}`           | Replace a task's fields          | `200`, or `404`                  |
| `PATCH`  | `/api/tasks/{id}/complete`  | Mark completed                   | `200`, or `404`                  |
| `DELETE` | `/api/tasks/{id}`           | Delete a task                    | `204`, or `404`                  |

#### New requirement — `@Value`-driven configuration

Add these to `application.yml`, and read them with `@Value`, not hardcoded:

yaml

```yaml
tasktracker:
  max-tasks: 50
  default-page-size: 10
```


1. **`POST /api/tasks`** must reject creation with `409 Conflict` once the store already holds `maxTasks` tasks — forces you to read `maxTasks` at request time, not just inject it and ignore it.
2. **`GET /api/tasks`** must support `?limit=` as an optional query param, falling back to `defaultPageSize` when the caller doesn't pass one.