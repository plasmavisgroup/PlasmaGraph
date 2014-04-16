package org.pvg.plasmagraph.utils.tools;

import org.pvg.plasmagraph.utils.types.AlphaType;

/**
 * Compares the Pearson Coefficient R values provided by the Interpolator Class
 * to values provided by the standardized Pearson critical value table. (Example
 * found in {@link http
 * ://www.gifted.uconn.edu/siegle/research/correlation/corrchrt.htm}, but
 * <b>MANY</b> others exist!)
 * 
 * A note should be made for negative values provided by the Interpolator Class;
 * as stated in {@link http
 * ://www.gifted.uconn.edu/siegle/research/correlation/alphaleve.htm}, the
 * negative component of an R-value does not detract from an interpolation's
 * validity; the negative component signifies its direction (upwards tendencies
 * yield positive R values, whereas downward tendencies yield negative values.),
 * whereas its magnitude is what determines the confidence the user should have
 * with said interpolation.
 * 
 * @author Gerardo A. Navas Morales
 */
public class DataConfidence {

	/**
	 * Provides a Confidence Interval value that can be then used to assume a
	 * correlation between two columns of data or not.
	 * 
	 * @param number_of_data_points
	 * @param alpha
	 * @return
	 * @throws Exception
	 */
	private static double getConfidenceInterval (int number_of_data_points,
			AlphaType alpha) throws Exception {

		int df = number_of_data_points - 2;
		double ci = 0.0;

		switch (df) {
		case 1:
			if (alpha.getValue () == .01) {
				ci = .9999;
			} else if (alpha.getValue () == .02) {
				ci = .9995;
			} else if (alpha.getValue () == .05) {
				ci = .997;
			} else if (alpha.getValue () == .10) {
				ci = .988;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 2:
			if (alpha.getValue () == .01) {
				ci = .990;
			} else if (alpha.getValue () == .02) {
				ci = .980;
			} else if (alpha.getValue () == .05) {
				ci = .950;
			} else if (alpha.getValue () == .10) {
				ci = .900;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 3:
			if (alpha.getValue () == .01) {
				ci = .959;
			} else if (alpha.getValue () == .02) {
				ci = .934;
			} else if (alpha.getValue () == .05) {
				ci = .878;
			} else if (alpha.getValue () == .10) {
				ci = .805;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 4:
			if (alpha.getValue () == .01) {
				ci = .917;
			} else if (alpha.getValue () == .02) {
				ci = .882;
			} else if (alpha.getValue () == .05) {
				ci = .811;
			} else if (alpha.getValue () == .10) {
				ci = .729;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 5:
			if (alpha.getValue () == .01) {
				ci = .874;
			} else if (alpha.getValue () == .02) {
				ci = .833;
			} else if (alpha.getValue () == .05) {
				ci = .754;
			} else if (alpha.getValue () == .10) {
				ci = .669;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 6:
			if (alpha.getValue () == .01) {
				ci = .834;
			} else if (alpha.getValue () == .02) {
				ci = .789;
			} else if (alpha.getValue () == .05) {
				ci = .707;
			} else if (alpha.getValue () == .10) {
				ci = .622;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 7:
			if (alpha.getValue () == .01) {
				ci = .798;
			} else if (alpha.getValue () == .02) {
				ci = .750;
			} else if (alpha.getValue () == .05) {
				ci = .666;
			} else if (alpha.getValue () == .10) {
				ci = .582;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 8:
			if (alpha.getValue () == .01) {
				ci = .765;
			} else if (alpha.getValue () == .02) {
				ci = .716;
			} else if (alpha.getValue () == .05) {
				ci = .632;
			} else if (alpha.getValue () == .10) {
				ci = .549;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 9:
			if (alpha.getValue () == .01) {
				ci = .735;
			} else if (alpha.getValue () == .02) {
				ci = .685;
			} else if (alpha.getValue () == .05) {
				ci = .602;
			} else if (alpha.getValue () == .10) {
				ci = .521;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 10:
			if (alpha.getValue () == .01) {
				ci = .708;
			} else if (alpha.getValue () == .02) {
				ci = .658;
			} else if (alpha.getValue () == .05) {
				ci = .576;
			} else if (alpha.getValue () == .10) {
				ci = .497;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 11:
			if (alpha.getValue () == .01) {
				ci = .684;
			} else if (alpha.getValue () == .02) {
				ci = .634;
			} else if (alpha.getValue () == .05) {
				ci = .553;
			} else if (alpha.getValue () == .10) {
				ci = .476;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 12:
			if (alpha.getValue () == .01) {
				ci = .661;
			} else if (alpha.getValue () == .02) {
				ci = .612;
			} else if (alpha.getValue () == .05) {
				ci = .532;
			} else if (alpha.getValue () == .10) {
				ci = .458;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 13:
			if (alpha.getValue () == .01) {
				ci = .641;
			} else if (alpha.getValue () == .02) {
				ci = .592;
			} else if (alpha.getValue () == .05) {
				ci = .514;
			} else if (alpha.getValue () == .10) {
				ci = .441;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 14:
			if (alpha.getValue () == .01) {
				ci = .623;
			} else if (alpha.getValue () == .02) {
				ci = .574;
			} else if (alpha.getValue () == .05) {
				ci = .497;
			} else if (alpha.getValue () == .10) {
				ci = .426;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 15:
			if (alpha.getValue () == .01) {
				ci = .606;
			} else if (alpha.getValue () == .02) {
				ci = .558;
			} else if (alpha.getValue () == .05) {
				ci = .482;
			} else if (alpha.getValue () == .10) {
				ci = .412;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 16:
			if (alpha.getValue () == .01) {
				ci = .590;
			} else if (alpha.getValue () == .02) {
				ci = .542;
			} else if (alpha.getValue () == .05) {
				ci = .468;
			} else if (alpha.getValue () == .10) {
				ci = .400;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 17:
			if (alpha.getValue () == .01) {
				ci = .575;
			} else if (alpha.getValue () == .02) {
				ci = .528;
			} else if (alpha.getValue () == .05) {
				ci = .456;
			} else if (alpha.getValue () == .10) {
				ci = .389;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 18:
			if (alpha.getValue () == .01) {
				ci = .561;
			} else if (alpha.getValue () == .02) {
				ci = .516;
			} else if (alpha.getValue () == .05) {
				ci = .444;
			} else if (alpha.getValue () == .10) {
				ci = .378;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 19:
			if (alpha.getValue () == .01) {
				ci = .549;
			} else if (alpha.getValue () == .02) {
				ci = .503;
			} else if (alpha.getValue () == .05) {
				ci = .433;
			} else if (alpha.getValue () == .10) {
				ci = .369;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 20:
			if (alpha.getValue () == .01) {
				ci = .537;
			} else if (alpha.getValue () == .02) {
				ci = .492;
			} else if (alpha.getValue () == .05) {
				ci = .423;
			} else if (alpha.getValue () == .10) {
				ci = .360;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 21:
			if (alpha.getValue () == .01) {
				ci = .526;
			} else if (alpha.getValue () == .02) {
				ci = .482;
			} else if (alpha.getValue () == .05) {
				ci = .413;
			} else if (alpha.getValue () == .10) {
				ci = .352;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 22:
			if (alpha.getValue () == .01) {
				ci = .515;
			} else if (alpha.getValue () == .02) {
				ci = .472;
			} else if (alpha.getValue () == .05) {
				ci = .404;
			} else if (alpha.getValue () == .10) {
				ci = .344;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 23:
			if (alpha.getValue () == .01) {
				ci = .505;
			} else if (alpha.getValue () == .02) {
				ci = .462;
			} else if (alpha.getValue () == .05) {
				ci = .396;
			} else if (alpha.getValue () == .10) {
				ci = .337;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 24:
			if (alpha.getValue () == .01) {
				ci = .496;
			} else if (alpha.getValue () == .02) {
				ci = .453;
			} else if (alpha.getValue () == .05) {
				ci = .388;
			} else if (alpha.getValue () == .10) {
				ci = .330;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 25:
			if (alpha.getValue () == .01) {
				ci = .487;
			} else if (alpha.getValue () == .02) {
				ci = .445;
			} else if (alpha.getValue () == .05) {
				ci = .381;
			} else if (alpha.getValue () == .10) {
				ci = .323;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 26:
			if (alpha.getValue () == .01) {
				ci = .479;
			} else if (alpha.getValue () == .02) {
				ci = .437;
			} else if (alpha.getValue () == .05) {
				ci = .374;
			} else if (alpha.getValue () == .10) {
				ci = .317;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 27:
			if (alpha.getValue () == .01) {
				ci = .471;
			} else if (alpha.getValue () == .02) {
				ci = .430;
			} else if (alpha.getValue () == .05) {
				ci = .367;
			} else if (alpha.getValue () == .10) {
				ci = .311;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 28:
			if (alpha.getValue () == .01) {
				ci = .463;
			} else if (alpha.getValue () == .02) {
				ci = .423;
			} else if (alpha.getValue () == .05) {
				ci = .361;
			} else if (alpha.getValue () == .10) {
				ci = .306;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 29:
			if (alpha.getValue () == .01) {
				ci = .456;
			} else if (alpha.getValue () == .02) {
				ci = .416;
			} else if (alpha.getValue () == .05) {
				ci = .355;
			} else if (alpha.getValue () == .10) {
				ci = .301;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		case 30:
			if (alpha.getValue () == .01) {
				ci = .449;
			} else if (alpha.getValue () == .02) {
				ci = .409;
			} else if (alpha.getValue () == .05) {
				ci = .349;
			} else if (alpha.getValue () == .10) {
				ci = .296;
			} else {
				throw (new Exception ("Alpha of non-default values."));
			}

			break;
		default:
			if (df >= 100) {
				if (alpha.getValue () == .01) {
					ci = .254;
				} else if (alpha.getValue () == .02) {
					ci = .230;
				} else if (alpha.getValue () == .05) {
					ci = .195;
				} else if (alpha.getValue () == .10) {
					ci = .164;
				} else {
					throw (new Exception ("Alpha of non-default values."));
				}
			} else if (df >= 90) {
				if (alpha.getValue () == .01) {
					ci = .267;
				} else if (alpha.getValue () == .02) {
					ci = .242;
				} else if (alpha.getValue () == .05) {
					ci = .205;
				} else if (alpha.getValue () == .10) {
					ci = .173;
				} else {
					throw (new Exception ("Alpha of non-default values."));
				}
			} else if (df >= 80) {
				if (alpha.getValue () == .01) {
					ci = .283;
				} else if (alpha.getValue () == .02) {
					ci = .256;
				} else if (alpha.getValue () == .05) {
					ci = .217;
				} else if (alpha.getValue () == .10) {
					ci = .183;
				} else {
					throw (new Exception ("Alpha of non-default values."));
				}
			} else if (df >= 70) {
				if (alpha.getValue () == .01) {
					ci = .303;
				} else if (alpha.getValue () == .02) {
					ci = .274;
				} else if (alpha.getValue () == .05) {
					ci = .232;
				} else if (alpha.getValue () == .10) {
					ci = .195;
				} else {
					throw (new Exception ("Alpha of non-default values."));
				}
			} else if (df >= 60) {
				if (alpha.getValue () == .01) {
					ci = .325;
				} else if (alpha.getValue () == .02) {
					ci = .295;
				} else if (alpha.getValue () == .05) {
					ci = .250;
				} else if (alpha.getValue () == .10) {
					ci = .211;
				} else {
					throw (new Exception ("Alpha of non-default values."));
				}
			} else if (df >= 50) {
				if (alpha.getValue () == .01) {
					ci = .354;
				} else if (alpha.getValue () == .02) {
					ci = .322;
				} else if (alpha.getValue () == .05) {
					ci = .273;
				} else if (alpha.getValue () == .10) {
					ci = .231;
				} else {
					throw (new Exception ("Alpha of non-default values."));
				}
			} else if (df >= 45) {
				if (alpha.getValue () == .01) {
					ci = .372;
				} else if (alpha.getValue () == .02) {
					ci = .338;
				} else if (alpha.getValue () == .05) {
					ci = .288;
				} else if (alpha.getValue () == .10) {
					ci = .243;
				} else {
					throw (new Exception ("Alpha of non-default values."));
				}
			} else if (df >= 40) {
				if (alpha.getValue () == .01) {
					ci = .393;
				} else if (alpha.getValue () == .02) {
					ci = .358;
				} else if (alpha.getValue () == .05) {
					ci = .304;
				} else if (alpha.getValue () == .10) {
					ci = .257;
				} else {
					throw (new Exception ("Alpha of non-default values."));
				}
			} else if (df >= 35) {
				if (alpha.getValue () == .01) {
					ci = .418;
				} else if (alpha.getValue () == .02) {
					ci = .381;
				} else if (alpha.getValue () == .05) {
					ci = .325;
				} else if (alpha.getValue () == .10) {
					ci = .275;
				} else {
					throw (new Exception ("Alpha of non-default values."));
				}
			} else { // if (df >= 30)
				if (alpha.getValue () == .01) {
					ci = .449;
				} else if (alpha.getValue () == .02) {
					ci = .409;
				} else if (alpha.getValue () == .05) {
					ci = .349;
				} else if (alpha.getValue () == .10) {
					ci = .296;
				} else {
					throw (new Exception ("Alpha of non-default values."));
				}
			}
		}

		return (ci);
	}

	/**
	 * Comparing method. Checks if the CI is less than or equal to the Pearson
	 * Coefficient.
	 * 
	 * @param ci
	 *            Confidence Interval value obtained from the
	 *            "getConfidenceInterval" method in this class.
	 * @param r
	 *            The R value provided by the interpolation.
	 * @return Boolean stating whether the Coefficient is valid or not.
	 */
	private static boolean isValid (double ci, double r) {
		return (Math.abs (r) > ci);
	}

	private static AlphaType getHighestCI (double r, int n) throws Exception {
		if (isValid (getConfidenceInterval (n, AlphaType.CI99), r)) {
			return (AlphaType.CI99);
		} else if (isValid (getConfidenceInterval (n, AlphaType.CI98), r)) {
			return (AlphaType.CI98);
		} else if (isValid (getConfidenceInterval (n, AlphaType.CI95), r)) {
			return (AlphaType.CI95);
		} else if (isValid (getConfidenceInterval (n, AlphaType.CI90), r)) {
			return (AlphaType.CI90);
		} else {
			return (AlphaType.INVALID);
		}
	}

	public static String provideCIValidity (double r, int n) {
		AlphaType ci;
		try {
			ci = getHighestCI (r, n);
			if (AlphaType.CI99.equals (ci)) {
				return ("There exists a correlation between both variables with a 99% Confidence.");
			} else if (AlphaType.CI98.equals (ci)) {
				return ("There exists a correlation between both variables with a 98% Confidence.");
			} else if (AlphaType.CI95.equals (ci)) {
				return ("There exists a correlation between both variables with a 95% Confidence.");
			} else if (AlphaType.CI90.equals (ci)) {
				return ("There exists a correlation between both variables with a 90% Confidence.");
			} else {
				return ("A useful correlation between both variables does not exist.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace ();
			return (e.toString ());
		}
	}
}