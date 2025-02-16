CREATE TABLE courses
(
    id               UUID PRIMARY KEY             DEFAULT gen_random_uuid(),
    title            VARCHAR(150) UNIQUE NOT NULL,
    description      TEXT                NOT NULL,
    created_at       TIMESTAMP           NOT NULL DEFAULT NOW(),
    created_by       UUID                NOT NULL,
    last_modified_at TIMESTAMP           NOT NULL DEFAULT NOW(),
    last_modified_by UUID                NOT NULL
);

CREATE TABLE topics
(
    id               UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    title            VARCHAR(100) NOT NULL,
    order_id         INTEGER      NOT NULL,
    course_id        UUID REFERENCES courses (id) ON DELETE CASCADE,
    created_at       TIMESTAMP    NOT NULL DEFAULT NOW(),
    created_by       UUID         NOT NULL,
    last_modified_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    last_modified_by UUID         NOT NULL,
    UNIQUE (order_id, course_id)
);

CREATE TABLE topic_items
(
    id               UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    title            VARCHAR(100) NOT NULL,
    type             VARCHAR(50)  NOT NULL,
    order_id         INTEGER      NOT NULL,
    topic_id         UUID REFERENCES topics (id) ON DELETE CASCADE,
    created_at       TIMESTAMP    NOT NULL DEFAULT NOW(),
    created_by       UUID         NOT NULL,
    last_modified_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    last_modified_by UUID         NOT NULL,
    UNIQUE (order_id, topic_id)
);

CREATE TABLE lectures
(
    item_id          UUID REFERENCES topic_items (id) ON DELETE CASCADE,
    video_id         TEXT      NOT NULL,
    created_at       TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by       UUID      NOT NULL,
    last_modified_at TIMESTAMP NOT NULL DEFAULT NOW(),
    last_modified_by UUID      NOT NULL
);

CREATE TABLE resources
(
    item_id          UUID REFERENCES topic_items (id) ON DELETE CASCADE,
    content          TEXT      NOT NULL,
    created_at       TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by       UUID      NOT NULL,
    last_modified_at TIMESTAMP NOT NULL DEFAULT NOW(),
    last_modified_by UUID      NOT NULL
);