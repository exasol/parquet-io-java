# Software Architectural Design -- Parquet IO Java

## Introduction

### Terms and Abbreviations

<dl>
    <dt>PIOJ</dt><dd>Parquet IO Java</dd>
</dl>

### Requirement Overview

Please refer to the [System Requirement Specification](system_requirements.md)
for user-level requirements.

## Solution Strategy

## Building Blocks

This section introduces the building blocks of the software. Together those
building blocks make up the big picture of the software structure.

### `Row`
`dsn~row~1`

A `Row` is class that contains list of Parquet columns.

### `RowParquetReader`
`dsn~row-parquet-reader~1`

The `RowParquetReader` reads Parquet formatted data into a `Row` object.

### `ParquetConverter`
`dsn~parquet-converter~1`

The `ParquetConverter` is an abstraction of set column converters that read
Parquet data into memory objects.

## Runtime View

This section describes the runtime behavior of the software.

### Reading Parquet File Contents 
`dsn~read-parquet-file-contents~1`

The `RowParquetReader` reads contents of a Parquet file.

Covers:

* `req~accessing-decoded-rows~1`

Needs: impl, utest

### Converting Primitive Column Types
`dsn~converting-primitive-column-types~1`

The `ParquetConverter` allows to convert primitive Parquet types into in-memory
objects.

Covers:

* `req~reading-primitive-types~1`

Needs: impl, utest

### Converting Logical Column Types
`dsn~converting-logical-column-types~1`

The `ParquetConverter` allows to convert logical Parquet types into in-memory
objects.

Covers:

* `req~reading-logical-types~1`

Needs: impl, utest

### Converting Nested Column Types
`dsn~converting-nested-column-types~1`

The `ParquetConverter` allows to convert nested Parquet types into in-memory
objects.

Covers:

* `req~reading-nested-types~1`

Needs: impl, utest

## Acknowledgments

This document's section structure is derived from the
"[arc42](https://arc42.org/)" architectural template by Dr. Gernot Starke, Dr.
Peter Hruschka.
