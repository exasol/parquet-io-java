package com.exasol.parquetio.reader.converter

import java.util.ArrayList
import java.util.Collections
import java.util.List

import scala.collection.mutable.ArrayBuffer

/**
 * An interface for storing the converted Parquet values.
 *
 * Implementations of this interface choose internal structure depending on the
 * value converter.
 */
trait ValueHolder {

  /**
   * Resets the internal value holder data structure.
   */
  def reset(): Unit

  /**
   * Returns the values as immutable sequence.
   *
   * @return list of values for this value holder
   */
  def getValues(): List[Any]

  /**
   * Inserts a value to the given position.
   *
   * @param position position to insert value
   * @param value value to insert
   */
  def put(index: Int, value: Any): Unit

  /**
   * Returns an immutable list of values.
   *
   * @param array an internal iterable of elements
   * @return immutable list of values
   */
  protected def getImmutableValues(array: Iterable[Any]): List[Any] = {
    var result = new ArrayList[Any]()
    array.foreach(element => result.add(element))
    Collections.unmodifiableList(result)
  }
}

/**
 * An indexed value holder class.
 *
 * It sets converted Parquet values to a positional cell in the provided array.
 *
 * @param size the size of the internal structure
 */
final case class IndexedValueHolder(size: Int) extends ValueHolder {
  private[this] var array = Array.ofDim[Any](size)

  override def reset(): Unit = array = Array.ofDim[Any](size)
  override def getValues(): List[Any] = getImmutableValues(array)
  override def put(index: Int, value: Any): Unit = array.update(index, value)
}

/**
 * An appending value holder class.
 *
 * It append converted Parquet values to the end of the values array.
 */
final case class AppendedValueHolder() extends ValueHolder {
  private[this] var array = ArrayBuffer.empty[Any]

  override def reset(): Unit = array.clear()
  override def getValues(): List[Any] = getImmutableValues(array)
  override def put(index: Int, value: Any): Unit = array.append(value)
}

/**
 * An empty value holder used for top-level parent converter.
 */
object EmptyValueHolder extends ValueHolder {
  override def reset(): Unit = ()
  override def getValues(): List[Any] = Collections.emptyList()
  override def put(index: Int, value: Any): Unit = ()
}
