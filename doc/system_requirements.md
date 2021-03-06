# System Requirement Specification Parquet IO Java

## Introduction

Parquet IO Java (PIOJ) is a Java library to access Apache Parquet files. Users
can use this library to read Parquet rows into Java objects.

## About This Document

### Target Audience

The target audience are software developers that use Java Virtual Machine (JVM)
based programming languages. See section ["Stakeholders"](#stakeholders) for
more details.

### Goal

The goal of Parquet IO Java is to provide reliable and efficient programmatic
access to Parquet files without using distributed compute engines.

## Stakeholders

Software Developers use PIOJ to programmatically access Parquet files.

## Terms and Abbreviations

The following list gives you an overview of terms and abbreviations commonly
used in PIOJ documents.

<dl>
<dt>Parquet</dt><dd>A columnar storage format.</dd>
<dt>Parquet File</dt><dd>A file that contains Parquet formatted data.</dd>
<dt>Row</dt><dd>A abstraction over list of columns in Parquet formatted data.</dd>
<dt>Row Group</dt><dd>A logical horizontal partitioning of the data into rows.</dd>
</dl>

## Features

Features are the highest level requirements in this document that describe the
main functionality of Parquet IO Java.

### Parquet Rows 
`feat~parquet-rows~1`

PIOJ allows user to access Parquet rows as Java objects.

Rationale:

Users should be able to obtain all row values at once. Similarly, users should
be able to access a single column value using column index or column name.

Needs: req

### Column Data Reading
`feat~column-data-reading~1`

PIOJ efficiently reads Parquet formatted column data into memory.

Rationale:

Depending on the encoding of column data type, different deserialization
mechanism should be used to improve the performance. For example, in-memory data
structure should be utilized if data type is encoded using dictionary encoding.

Needs: req

### Row Group Intervals Reading
`feat~row-group-intervals-reading~1`

PIOJ allows reading certain interval of row groups from a Parquet file.

Needs: req

## Functional Requirements

This section lists functional requirements from the user's perspective. The
requirements are grouped by feature where they belong to a single feature.

### Accessing Decoded Rows
`req~accessing-decoded-rows~1`

PIOJ creates a Row containing list of Parquet columns.

Covers:

- [`feat~parquet-rows~1`](#parquet-rows)

Needs: dsn

### Reading Primitive Types
`req~reading-primitive-types~1`

PIOJ reads primitive types from Parquet formatted files.

Covers:

- [`feat~column-data-reading~1`](#column-data-reading)

Needs: dsn

### Reading Logical Types
`req~reading-logical-types~1`

PIOJ reads logical types from Parquet formatted files.

Rationale:

Parquet uses annotated types such as `INT32`, `INT64` to store logical types
such as `DATE` or `TIMESTAMP`.

Covers:

- [`feat~column-data-reading~1`](#column-data-reading)

Needs: dsn

### Reading Nested Types
`req~reading-nested-types~1`

PIOJ reads nested types from Parquet formatted files.

Rationale:

Parquet encodes nested types such as `LIST` or `MAP` by adding group levels
around repeated fields.

Covers:

- [`feat~column-data-reading~1`](#column-data-reading)

Needs: dsn

### Reading Row Group Intervals
`req~reading-row-group-intervals~1`

PIOJ only reads data of provided row group intervals that contain starting and
ending indexes.

Rationale:

This allows to skip reading certain row groups of Parquet file. Therefore, users
can virtually split and read in parallel Parquet files.

Covers:

- [`feat~row-group-intervals-reading~1`](#row-group-intervals-reading)

Needs: dsn
