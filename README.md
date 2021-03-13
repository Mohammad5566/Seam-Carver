# Seam Carver

[Seam Carving](https://www.wikiwand.com/en/Seam_carving) is a content-aware image resizing technique where the image is reduced in size by one pixel of height or width at a time. A vertical seam in an image is a path of pixels connected from the top to the bottom with one pixel in each row. A horizontal seam is a path of pixels connected from the left to the right with one pixel in each column.  

### Finding and Removing a Seam:

**Energy calculation:** The first step is to calculate the energy of each pixel, which is a measure of the importance of each pixel—the higher the energy, the less likely that the pixel will be included as part of a seam. The energy of a pixel is compute using the dual gradient energy function. A high-energy pixel corresponds to a pixel where there is a sudden change in color. The seam-carving technique avoids removing such high-energy pixels to preserve as much of the orignal image's properties as it can. Pixels with higher energy values have whiter values.  
The energy picture the image below is shown on the right.




<p align="middle">
  <img src="https://i.imgur.com/eIQVUYP.jpg" width="450" />
  <img src="https://i.imgur.com/pIaHQVx.jpg" width="450" /> 
</p>


**Seam Identification:** The next step is to find a vertical seam of minimum total energy. This is similar to the classic shortest path problem in an edge-weighted digraph except for the following:
 * The weights are on the vertices instead of the edges.
 * We want to find the shortest path from any of W pixels in the top row to any of the W pixels in the bottom row.
 * The digraph is acyclic, where there is a downward edge from pixel (x, y) to pixels (x − 1, y + 1), (x, y + 1), and (x + 1, y + 1), assuming that the coordinates are in the     prescribed range.

**Seam Removal:** The final step is remove from the image all of the pixels along the seam. The logic for this method has been implemented for you in the supplementary SeamRemover class, provided in SeamRemover.java.
