package phase1Assessment;

public class FileName {

  private String filename;

  public FileName() {}

  public FileName(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  @Override
  public String toString() {
    return "FileName [filename=" + filename + "]";
  }
}