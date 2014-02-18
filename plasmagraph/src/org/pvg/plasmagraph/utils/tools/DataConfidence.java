package org.pvg.plasmagraph.utils.tools;

import org.pvg.plasmagraph.utils.IncorrectAlphaError;

public class DataConfidence {

	/**
	 * Provides a Confidence Interval value that can be then used to assume
	 * a correlation between two columns of data or not.
	 * 
	 * @param pearson_coefficient
	 * @param number_of_data_points
	 * @param alpha
	 * @return
	 * @throws IncorrectAlphaError
	 */
	public static double getConfidenceInterval (double pearson_coefficient,
			int number_of_data_points, double alpha) throws IncorrectAlphaError {
		int df = number_of_data_points - 2;
		double ci = 0.0;

		switch (df) {
		case 1:
			if (alpha == .10) {
				ci = .9999;
			} else if (alpha == .05) {
				ci = .9995;
			} else if (alpha == .02) {
				ci = .997;
			} else if (alpha == .01) {
				ci = .988;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 2:
			if (alpha == .10) {
				ci = .990;
			} else if (alpha == .05) {
				ci = .980;
			} else if (alpha == .02) {
				ci = .950;
			} else if (alpha == .01) {
				ci = .900;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 3:
			if (alpha == .10) {
				ci = .959;
			} else if (alpha == .05) {
				ci = .934;
			} else if (alpha == .02) {
				ci = .878;
			} else if (alpha == .01) {
				ci = .805;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 4:
			if (alpha == .10) {
				ci = .917;
			} else if (alpha == .05) {
				ci = .882;
			} else if (alpha == .02) {
				ci = .811;
			} else if (alpha == .01) {
				ci = .729;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 5:
			if (alpha == .10) {
				ci = .874;
			} else if (alpha == .05) {
				ci = .833;
			} else if (alpha == .02) {
				ci = .754;
			} else if (alpha == .01) {
				ci = .669;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 6:
			if (alpha == .10) {
				ci = .834;
			} else if (alpha == .05) {
				ci = .789;
			} else if (alpha == .02) {
				ci = .707;
			} else if (alpha == .01) {
				ci = .622;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 7:
			if (alpha == .10) {
				ci = .798;
			} else if (alpha == .05) {
				ci = .750;
			} else if (alpha == .02) {
				ci = .666;
			} else if (alpha == .01) {
				ci = .582;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 8:
			if (alpha == .10) {
				ci = .765;
			} else if (alpha == .05) {
				ci = .716;
			} else if (alpha == .02) {
				ci = .632;
			} else if (alpha == .01) {
				ci = .549;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 9:
			if (alpha == .10) {
				ci = .735;
			} else if (alpha == .05) {
				ci = .685;
			} else if (alpha == .02) {
				ci = .602;
			} else if (alpha == .01) {
				ci = .521;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 10:
			if (alpha == .10) {
				ci = .708;
			} else if (alpha == .05) {
				ci = .658;
			} else if (alpha == .02) {
				ci = .576;
			} else if (alpha == .01) {
				ci = .497;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 11:
			if (alpha == .10) {
				ci = .684;
			} else if (alpha == .05) {
				ci = .634;
			} else if (alpha == .02) {
				ci = .553;
			} else if (alpha == .01) {
				ci = .476;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 12:
			if (alpha == .10) {
				ci = .661;
			} else if (alpha == .05) {
				ci = .612;
			} else if (alpha == .02) {
				ci = .532;
			} else if (alpha == .01) {
				ci = .458;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 13:
			if (alpha == .10) {
				ci = .641;
			} else if (alpha == .05) {
				ci = .592;
			} else if (alpha == .02) {
				ci = .514;
			} else if (alpha == .01) {
				ci = .441;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 14:
			if (alpha == .10) {
				ci = .623;
			} else if (alpha == .05) {
				ci = .574;
			} else if (alpha == .02) {
				ci = .497;
			} else if (alpha == .01) {
				ci = .426;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 15:
			if (alpha == .10) {
				ci = .606;
			} else if (alpha == .05) {
				ci = .558;
			} else if (alpha == .02) {
				ci = .482;
			} else if (alpha == .01) {
				ci = .412;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 16:
			if (alpha == .10) {
				ci = .590;
			} else if (alpha == .05) {
				ci = .542;
			} else if (alpha == .02) {
				ci = .468;
			} else if (alpha == .01) {
				ci = .400;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 17:
			if (alpha == .10) {
				ci = .575;
			} else if (alpha == .05) {
				ci = .528;
			} else if (alpha == .02) {
				ci = .456;
			} else if (alpha == .01) {
				ci = .389;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 18:
			if (alpha == .10) {
				ci = .561;
			} else if (alpha == .05) {
				ci = .516;
			} else if (alpha == .02) {
				ci = .444;
			} else if (alpha == .01) {
				ci = .378;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 19:
			if (alpha == .10) {
				ci = .549;
			} else if (alpha == .05) {
				ci = .503;
			} else if (alpha == .02) {
				ci = .433;
			} else if (alpha == .01) {
				ci = .369;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 20:
			if (alpha == .10) {
				ci = .537;
			} else if (alpha == .05) {
				ci = .492;
			} else if (alpha == .02) {
				ci = .423;
			} else if (alpha == .01) {
				ci = .360;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 21:
			if (alpha == .10) {
				ci = .526;
			} else if (alpha == .05) {
				ci = .482;
			} else if (alpha == .02) {
				ci = .413;
			} else if (alpha == .01) {
				ci = .352;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 22:
			if (alpha == .10) {
				ci = .515;
			} else if (alpha == .05) {
				ci = .472;
			} else if (alpha == .02) {
				ci = .404;
			} else if (alpha == .01) {
				ci = .344;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 23:
			if (alpha == .10) {
				ci = .505;
			} else if (alpha == .05) {
				ci = .462;
			} else if (alpha == .02) {
				ci = .396;
			} else if (alpha == .01) {
				ci = .337;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 24:
			if (alpha == .10) {
				ci = .496;
			} else if (alpha == .05) {
				ci = .453;
			} else if (alpha == .02) {
				ci = .388;
			} else if (alpha == .01) {
				ci = .330;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 25:
			if (alpha == .10) {
				ci = .487;
			} else if (alpha == .05) {
				ci = .445;
			} else if (alpha == .02) {
				ci = .381;
			} else if (alpha == .01) {
				ci = .323;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 26:
			if (alpha == .10) {
				ci = .479;
			} else if (alpha == .05) {
				ci = .437;
			} else if (alpha == .02) {
				ci = .374;
			} else if (alpha == .01) {
				ci = .317;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 27:
			if (alpha == .10) {
				ci = .471;
			} else if (alpha == .05) {
				ci = .430;
			} else if (alpha == .02) {
				ci = .367;
			} else if (alpha == .01) {
				ci = .311;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 28:
			if (alpha == .10) {
				ci = .463;
			} else if (alpha == .05) {
				ci = .423;
			} else if (alpha == .02) {
				ci = .361;
			} else if (alpha == .01) {
				ci = .306;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 29:
			if (alpha == .10) {
				ci = .456;
			} else if (alpha == .05) {
				ci = .416;
			} else if (alpha == .02) {
				ci = .355;
			} else if (alpha == .01) {
				ci = .301;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		case 30:
			if (alpha == .10) {
				ci = .449;
			} else if (alpha == .05) {
				ci = .409;
			} else if (alpha == .02) {
				ci = .349;
			} else if (alpha == .01) {
				ci = .296;
			} else {
				throw (new IncorrectAlphaError ("Alpha of non-default values."));
			}

			break;
		default:
			if (df >= 100) {
				if (alpha == .10) {
					ci = .254;
				} else if (alpha == .05) {
					ci = .230;
				} else if (alpha == .02) {
					ci = .195;
				} else if (alpha == .01) {
					ci = .164;
				} else {
					throw (new IncorrectAlphaError (
							"Alpha of non-default values."));
				}
			} else if (df >= 90) {
				if (alpha == .10) {
					ci = .267;
				} else if (alpha == .05) {
					ci = .242;
				} else if (alpha == .02) {
					ci = .205;
				} else if (alpha == .01) {
					ci = .173;
				} else {
					throw (new IncorrectAlphaError (
							"Alpha of non-default values."));
				}
			} else if (df >= 80) {
				if (alpha == .10) {
					ci = .283;
				} else if (alpha == .05) {
					ci = .256;
				} else if (alpha == .02) {
					ci = .217;
				} else if (alpha == .01) {
					ci = .183;
				} else {
					throw (new IncorrectAlphaError (
							"Alpha of non-default values."));
				}
			} else if (df >= 70) {
				if (alpha == .10) {
					ci = .303;
				} else if (alpha == .05) {
					ci = .274;
				} else if (alpha == .02) {
					ci = .232;
				} else if (alpha == .01) {
					ci = .195;
				} else {
					throw (new IncorrectAlphaError (
							"Alpha of non-default values."));
				}
			} else if (df >= 60) {
				if (alpha == .10) {
					ci = .325;
				} else if (alpha == .05) {
					ci = .295;
				} else if (alpha == .02) {
					ci = .250;
				} else if (alpha == .01) {
					ci = .211;
				} else {
					throw (new IncorrectAlphaError (
							"Alpha of non-default values."));
				}
			} else if (df >= 50) {
				if (alpha == .10) {
					ci = .354;
				} else if (alpha == .05) {
					ci = .322;
				} else if (alpha == .02) {
					ci = .273;
				} else if (alpha == .01) {
					ci = .231;
				} else {
					throw (new IncorrectAlphaError (
							"Alpha of non-default values."));
				}
			} else if (df >= 45) {
				if (alpha == .10) {
					ci = .372;
				} else if (alpha == .05) {
					ci = .338;
				} else if (alpha == .02) {
					ci = .288;
				} else if (alpha == .01) {
					ci = .243;
				} else {
					throw (new IncorrectAlphaError (
							"Alpha of non-default values."));
				}
			} else if (df >= 40) {
				if (alpha == .10) {
					ci = .393;
				} else if (alpha == .05) {
					ci = .358;
				} else if (alpha == .02) {
					ci = .304;
				} else if (alpha == .01) {
					ci = .257;
				} else {
					throw (new IncorrectAlphaError (
							"Alpha of non-default values."));
				}
			} else if (df >= 35) {
				if (alpha == .10) {
					ci = .418;
				} else if (alpha == .05) {
					ci = .381;
				} else if (alpha == .02) {
					ci = .325;
				} else if (alpha == .01) {
					ci = .275;
				} else {
					throw (new IncorrectAlphaError (
							"Alpha of non-default values."));
				}
			} else { // if (df >= 30)
				if (alpha == .10) {
					ci = .449;
				} else if (alpha == .05) {
					ci = .409;
				} else if (alpha == .02) {
					ci = .349;
				} else if (alpha == .01) {
					ci = .296;
				} else {
					throw (new IncorrectAlphaError (
							"Alpha of non-default values."));
				}
			}
		}

		return (ci);
	}
	
	public static boolean isValid (double ci, double r) {
		return (ci == r);
	}

	public static double getCoefficientOfDetermination (
			double pearson_coefficient) {
		return (Math.pow (pearson_coefficient, 2.0));
	}
}