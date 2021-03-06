package assignment2;

import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;

public class CoordinatesTransformer {

	public static void main (String[] args) throws TransformException {
		
		CoordinateReferenceSystem wgs84 = null;
		try {
			wgs84 = CRS.decode("EPSG:4326", true);
		} catch (NoSuchAuthorityCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CoordinateReferenceSystem utm33 = null;
		try {
			utm33 = CRS.decode("EPSG:32633", true);
		} catch (NoSuchAuthorityCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MathTransform wgs84Toutm33n = null;
		try {
			wgs84Toutm33n = CRS.findMathTransform(wgs84, utm33);
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		com.vividsolutions.jts.geom.GeometryFactory jtsGf = JTSFactoryFinder.getGeometryFactory();
		
		Point pointInWgs84 = (Point) jtsGf.createPoint(new Coordinate(13.060209, 47.788901));
		Point pointInUtm33n = (Point) JTS.transform(pointInWgs84, wgs84Toutm33n);
		
		System.out.println(pointInUtm33n);
	}//main
}//class
