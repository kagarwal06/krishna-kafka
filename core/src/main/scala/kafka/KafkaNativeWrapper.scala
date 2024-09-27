package kafka

import kafka.tools.StorageTool
import kafka.utils.Logging

object KafkaNativeWrapper extends Logging {
  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      throw new RuntimeException(s"Error: No operation input provided")
    }
    val operation = args.head
    val arguments = args.tail
    operation match {
      case "storage-tool" => StorageTool.main(arguments)
      case "kafka" => Kafka.main(arguments)
      case _ =>
        throw new RuntimeException(s"Unknown operation $operation. " +
          s"Please provide a valid operation: 'storage-tool' or 'kafka'.")
    }
  }
}
