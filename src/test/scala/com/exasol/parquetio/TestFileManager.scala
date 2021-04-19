package com.exasol.parquetio

import java.io.IOException
import java.nio.file._
import java.nio.file.attribute.BasicFileAttributes

/**
 * A mixin trait with file creation and deletion operations.
 */
trait TestFileManager {

  /**
   * Create a temporary folder.
   *
   * @param name the name of the directory.
   */
  final def createTemporaryFolder(name: String): Path =
    Files.createTempDirectory(name)

  /**
   * Recursively deletes files from the path.
   *
   * @param dir the path of a file or a directory.
   */
  final def deletePathFiles(dir: Path): Unit = {
    Files.walkFileTree(
      dir,
      new SimpleFileVisitor[Path] {
        override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
          Files.delete(file)
          FileVisitResult.CONTINUE
        }
        override def postVisitDirectory(dir: Path, exc: IOException): FileVisitResult = {
          Files.delete(dir)
          FileVisitResult.CONTINUE
        }
      }
    )
    ()
  }

}
