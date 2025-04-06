CREATE TABLE users (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  username TEXT NOT NULL UNIQUE,
  email TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL,
  avatar TEXT DEFAULT 'cld-sample',
  bio TEXT,
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  created_by UUID,
  last_modified_at TIMESTAMP NOT NULL DEFAULT NOW(),
  last_modified_by UUID
);

CREATE TABLE roles (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
  name TEXT NOT NULL UNIQUE
);

CREATE TABLE users_roles (
  user_id UUID NOT NULL,
  role_id UUID NOT NULL,
  PRIMARY KEY (user_id, role_id)
);

INSERT INTO
  roles (name)
VALUES
  ('ROLE_USER'),
  ('ROLE_ADMIN');