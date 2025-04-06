CREATE TABLE categories (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
  name TEXT UNIQUE NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  created_by UUID NOT NULL,
  last_modified_at TIMESTAMP NOT NULL DEFAULT NOW(),
  last_modified_by UUID NOT NULL
);

CREATE TABLE courses (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
  title TEXT UNIQUE NOT NULL,
  description TEXT NOT NULL,
  author_id UUID REFERENCES users (id) ON DELETE CASCADE,
  category_id UUID REFERENCES categories (id) ON DELETE CASCADE,
  level TEXT NOT NULL, -- BEGINNER, INTERMEDIATE, ADVANCED
  duration TEXT DEFAULT '0 min',
  price NUMERIC(10, 2) DEFAULT 0.00,
  rating NUMERIC(3, 2) CHECK (rating BETWEEN 0 AND 5),
  enrolled_students INTEGER DEFAULT 0,
  img TEXT DEFAULT 'samples/cup-on-a-table',
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  created_by UUID NOT NULL,
  last_modified_at TIMESTAMP NOT NULL DEFAULT NOW(),
  last_modified_by UUID NOT NULL
);

CREATE TABLE topics (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
  title TEXT NOT NULL,
  order_id INTEGER NOT NULL,
  course_id UUID REFERENCES courses (id) ON DELETE CASCADE,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  created_by UUID NOT NULL,
  last_modified_at TIMESTAMP NOT NULL DEFAULT NOW(),
  last_modified_by UUID NOT NULL,
  UNIQUE (order_id, course_id)
);

CREATE TABLE topic_items (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
  title TEXT NOT NULL,
  type TEXT NOT NULL,
  order_id INTEGER NOT NULL,
  topic_id UUID REFERENCES topics (id) ON DELETE CASCADE,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  created_by UUID NOT NULL,
  last_modified_at TIMESTAMP NOT NULL DEFAULT NOW(),
  last_modified_by UUID NOT NULL,
  UNIQUE (order_id, topic_id)
);

CREATE TABLE lectures (
  item_id UUID REFERENCES topic_items (id) ON DELETE CASCADE,
  video_id TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  created_by UUID NOT NULL,
  last_modified_at TIMESTAMP NOT NULL DEFAULT NOW(),
  last_modified_by UUID NOT NULL
);

CREATE TABLE resources (
  item_id UUID REFERENCES topic_items (id) ON DELETE CASCADE,
  content TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  created_by UUID NOT NULL,
  last_modified_at TIMESTAMP NOT NULL DEFAULT NOW(),
  last_modified_by UUID NOT NULL
);